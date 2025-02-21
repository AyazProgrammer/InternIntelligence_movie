package com.example.movie.presentation.view.fragment.main

import android.content.Context
import android.os.Handler
import android.os.Looper
import android.util.Log
import android.widget.Toast
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.fragment.findNavController
import androidx.navigation.ui.setupWithNavController
import com.example.movie.R
import com.example.movie.common.base.fragment.BaseFragment
import com.example.movie.common.factory.CommonViewModelFactory
import com.example.movie.data.remote.data_source.auth.AuthRemoteDataSourceImpl
import com.example.movie.data.remote.network.RetrofitInstance
import com.example.movie.data.repository.AuthRepositoryImpl
import com.example.movie.databinding.FragmentMovieBinding
import com.example.movie.domain.usecase.login_register.LoginUserUseCase
import com.example.movie.domain.usecase.login_register.RefreshTokenUseCase
import com.example.movie.domain.usecase.login_register.RegisterUserUseCase
import com.example.movie.domain.usecase.login_register.UserProfileUseCase
import com.example.movie.presentation.viewmodel.AuthViewModel

class MovieFragment :  BaseFragment<FragmentMovieBinding>(FragmentMovieBinding::inflate) {

    private val handler = Handler(Looper.getMainLooper())
    private val refreshRunnable = object : Runnable {
        override fun run() {
            refreshAccessToken()
            handler.postDelayed(this, 20 * 60 * 1000)
        }
    }
    private val viewModel: AuthViewModel by activityViewModels {
        CommonViewModelFactory {
            AuthViewModel(


                RegisterUserUseCase(
                    AuthRepositoryImpl(
                        AuthRemoteDataSourceImpl(
                            RetrofitInstance.authApiService
                        ),
                    )
                ),
                LoginUserUseCase(
                    AuthRepositoryImpl(
                        AuthRemoteDataSourceImpl(
                            RetrofitInstance.authApiService
                        ),
                    )
                ),
                RefreshTokenUseCase(
                    AuthRepositoryImpl(
                        AuthRemoteDataSourceImpl(
                            RetrofitInstance.authApiService
                        ),
                    )
                ),
                UserProfileUseCase(
                    AuthRepositoryImpl(
                        AuthRemoteDataSourceImpl(
                            RetrofitInstance.authApiService
                        ),
                    )
                ),

                )
        }
    }
    override fun setupCreatedUI(){

        val navHostFragment = childFragmentManager.findFragmentById(R.id.fragmentContainerView) as NavHostFragment
        val navController = navHostFragment.navController

        binding.bottomNavigation.setupWithNavController(navController)



        binding.bottomNavigation.setOnItemSelectedListener { menuItem ->
            when (menuItem.itemId) {
                R.id.nav_home -> {
                    navController.navigate(R.id.homeFragment)
                    true
                }
                R.id.nav_search -> {
                    navController.navigate(R.id.searchFragment)
                    true
                }
                R.id.nav_favorites -> {
                    navController.navigate(R.id.favoritesFragment)
                    true
                }
                R.id.nav_download -> {
                    navController.navigate(R.id.downloadsFragment)
                    true
                }
                R.id.nav_profile -> {
                    navController.navigate(R.id.profileFragment)
                    true
                }


                else -> false
            }
        }


    }

    override fun onResume() {
        super.onResume()
        handler.post(refreshRunnable)
    }

    override fun onPause() {
        super.onPause()
        handler.removeCallbacks(refreshRunnable)
    }

    private fun refreshAccessToken() {
        val sharedPreferences = requireContext().getSharedPreferences("auth", Context.MODE_PRIVATE)
        val refreshToken = sharedPreferences.getString("refreshToken", null)

        if (refreshToken != null) {

            viewModel.refreshToken(refreshToken)
        }
    }




}