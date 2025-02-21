package com.example.movie.presentation.view.fragment.bottomMenu

import android.content.Context
import android.util.Log
import android.widget.Toast
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.fragment.findNavController
import com.example.movie.R
import com.example.movie.common.base.fragment.BaseFragment
import com.example.movie.common.factory.CommonViewModelFactory
import com.example.movie.common.utils.ImageLoader
import com.example.movie.data.remote.data_source.auth.AuthRemoteDataSourceImpl
import com.example.movie.data.remote.network.RetrofitInstance
import com.example.movie.data.repository.AuthRepositoryImpl
import com.example.movie.databinding.FragmentProfileBinding
import com.example.movie.domain.usecase.login_register.LoginUserUseCase
import com.example.movie.domain.usecase.login_register.RefreshTokenUseCase
import com.example.movie.domain.usecase.login_register.RegisterUserUseCase
import com.example.movie.domain.usecase.login_register.UserProfileUseCase
import com.example.movie.presentation.view.fragment.main.LoginFragment
import com.example.movie.presentation.view.fragment.main.MovieFragment
import com.example.movie.presentation.viewmodel.AuthViewModel


class ProfileFragment : BaseFragment<FragmentProfileBinding>(FragmentProfileBinding::inflate) {

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
        authViewModel.userProfile.observe(viewLifecycleOwner) { profile ->
            binding.emailText.text = profile.email
            binding.usernameText.text = profile.name
            ImageLoader.loadCircularImage(binding.root.context, profile.avatar, binding.profileImage)


        }
        binding.exitButton.setOnClickListener {
            clearAllTokens()
            authViewModel.deleteUserLogIn()
            val mainNavHostFragment = requireActivity().supportFragmentManager.findFragmentById(R.id.fragmentContainerView2) as NavHostFragment
            val navController = mainNavHostFragment.navController
            navController.popBackStack(R.id.movieFragment, true)
            authViewModel.deleteUserLogIn()
            navController.navigate(R.id.loginFragment)



        }

    }
    private fun clearAllTokens() {
        val sharedPreferences = requireContext().getSharedPreferences("auth", Context.MODE_PRIVATE)
        with(sharedPreferences.edit()) {
            clear()
            apply()
        }
    }


}