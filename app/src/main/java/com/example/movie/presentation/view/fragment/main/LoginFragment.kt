package com.example.movie.presentation.view.fragment.main

import android.content.Context
import android.util.Log
import android.widget.Toast
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import com.example.movie.R
import com.example.movie.common.base.fragment.BaseFragment
import com.example.movie.common.extensions.addEmailFormatting
import com.example.movie.common.factory.CommonViewModelFactory
import com.example.movie.data.remote.data_source.auth.AuthRemoteDataSourceImpl
import com.example.movie.data.remote.model.request.login.AuthTokens
import com.example.movie.data.remote.network.RetrofitInstance
import com.example.movie.data.repository.AuthRepositoryImpl
import com.example.movie.databinding.FragmentLoginBinding
import com.example.movie.domain.usecase.login_register.LoginUserUseCase
import com.example.movie.domain.usecase.login_register.RefreshTokenUseCase
import com.example.movie.domain.usecase.login_register.RegisterUserUseCase
import com.example.movie.domain.usecase.login_register.UserProfileUseCase
import com.example.movie.presentation.viewmodel.AuthViewModel


class LoginFragment : BaseFragment<FragmentLoginBinding>(FragmentLoginBinding::inflate) {

    private val viewModel: AuthViewModel by activityViewModels {
        CommonViewModelFactory {
            AuthViewModel(
                RegisterUserUseCase(AuthRepositoryImpl(AuthRemoteDataSourceImpl(RetrofitInstance.authApiService))),
                LoginUserUseCase(AuthRepositoryImpl(AuthRemoteDataSourceImpl(RetrofitInstance.authApiService),)),
                RefreshTokenUseCase(AuthRepositoryImpl(AuthRemoteDataSourceImpl(RetrofitInstance.authApiService),)),
                UserProfileUseCase(AuthRepositoryImpl(AuthRemoteDataSourceImpl(RetrofitInstance.authApiService),)),
                )
        }
    }

    override fun setupCreatedUI() {

        val emailInput = binding.email
        val passwordInput = binding.password
        val loginButton = binding.loginButton
        emailInput.addEmailFormatting()

        loginButton.setOnClickListener {
            viewModel.login(
                email = emailInput.text.toString(),
                password = passwordInput.text.toString()
            )
        }

        viewModel.authTokens.observe(viewLifecycleOwner) { tokens ->

            if (!tokens.access_token.isNullOrBlank() and !tokens.refresh_token.isNullOrEmpty()) {
                saveTokens(tokens)
                viewModel.getUserProfile(tokens.access_token)
                Log.e("token", "$tokens")
                Toast.makeText(context, "Login Successful", Toast.LENGTH_SHORT).show()
                findNavController().navigate(R.id.action_loginFragment_to_movieFragment)
            }

        }

        viewModel.errorMessage.observe(viewLifecycleOwner) { message ->
            Toast.makeText(context, message, Toast.LENGTH_SHORT).show()
        }
        navigateTo(binding.registerLink, R.id.registerFragment2)
        // navigateTo(binding.loginButton, R.id.movieFragment)
//        binding.loginButton.setOnClickListener {
//            val intent = Intent(requireContext(), DashboardActivity::class.java)
//            startActivity(intent)
//        }

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