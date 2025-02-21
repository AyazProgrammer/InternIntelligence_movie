package com.example.movie.presentation.view.fragment.main

import android.content.Context
import android.util.Log
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import com.example.movie.R
import com.example.movie.common.base.fragment.BaseFragment
import com.example.movie.common.factory.CommonViewModelFactory
import com.example.movie.data.remote.data_source.auth.AuthRemoteDataSourceImpl
import com.example.movie.data.remote.model.request.login.AuthTokens
import com.example.movie.data.remote.network.RetrofitInstance
import com.example.movie.data.repository.AuthRepositoryImpl
import com.example.movie.databinding.FragmentSplashBinding
import com.example.movie.domain.usecase.login_register.LoginUserUseCase
import com.example.movie.domain.usecase.login_register.RefreshTokenUseCase
import com.example.movie.domain.usecase.login_register.RegisterUserUseCase
import com.example.movie.domain.usecase.login_register.UserProfileUseCase
import com.example.movie.presentation.viewmodel.AuthViewModel
import com.example.movie.presentation.viewmodel.SplashViewmodel
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch


class SplashFragment : BaseFragment<FragmentSplashBinding>(FragmentSplashBinding::inflate) {

    private val viewModel: SplashViewmodel by lazy {
        ViewModelProvider(this)[SplashViewmodel::class.java]
    }

    private val authViewModel: AuthViewModel by activityViewModels {
        CommonViewModelFactory {
            AuthViewModel(
                RegisterUserUseCase(AuthRepositoryImpl(AuthRemoteDataSourceImpl(RetrofitInstance.authApiService))),
                LoginUserUseCase(AuthRepositoryImpl(AuthRemoteDataSourceImpl(RetrofitInstance.authApiService))),
                RefreshTokenUseCase(AuthRepositoryImpl(AuthRemoteDataSourceImpl(RetrofitInstance.authApiService))),
                UserProfileUseCase(AuthRepositoryImpl(AuthRemoteDataSourceImpl(RetrofitInstance.authApiService)))
            )
        }
    }

    override fun setupCreatedUI() {
        // UI setup logic
    }

    override fun onResume() {
        super.onResume()

        viewModel.splash()
        lifecycleScope.launch {
            viewModel.splashTime.collectLatest { timeout ->
                if (timeout) {
                    handleAuthNavigation()
                }
            }
        }
    }

    private fun handleAuthNavigation() {
        val sharedPreferences = requireContext().getSharedPreferences("auth", Context.MODE_PRIVATE)
        val refreshToken = sharedPreferences.getString("refreshToken", null)
        Log.e("saved refresh token", "$refreshToken")
        if (!refreshToken.isNullOrEmpty()) {

            authViewModel.refreshToken(refreshToken)
        } else {
            findNavController().navigate(R.id.action_splashFragment_to_loginFragment)
            return
        }

        authViewModel.authTokens.observe(viewLifecycleOwner) { tokens ->
            saveTokens(tokens)
            authViewModel.getUserProfile(tokens.access_token)
            findNavController().navigate(R.id.action_splashFragment_to_movieFragment)
        }

        authViewModel.errorMessage.observe(viewLifecycleOwner) {
            findNavController().navigate(R.id.action_splashFragment_to_loginFragment)
        }
    }

    private fun saveTokens(tokens: AuthTokens) {
        val sharedPreferences = requireContext().getSharedPreferences("auth", Context.MODE_PRIVATE)
        with(sharedPreferences.edit()) {
            putString("accessToken", tokens.access_token)
            putString("refreshToken", tokens.refresh_token)
            apply()
        }
    }
}

