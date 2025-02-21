package com.example.movie.presentation.view.fragment.main

import android.widget.Toast
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import com.example.movie.R
import com.example.movie.common.base.fragment.BaseFragment
import com.example.movie.common.extensions.addEmailFormatting
import com.example.movie.common.extensions.addPhoneNumberFormatting
import com.example.movie.common.factory.CommonViewModelFactory
import com.example.movie.data.remote.data_source.auth.AuthRemoteDataSourceImpl
import com.example.movie.data.remote.model.request.register.User
import com.example.movie.data.remote.network.RetrofitInstance
import com.example.movie.data.repository.AuthRepositoryImpl
import com.example.movie.databinding.FragmentRegisterBinding
import com.example.movie.domain.usecase.login_register.LoginUserUseCase
import com.example.movie.domain.usecase.login_register.RefreshTokenUseCase
import com.example.movie.domain.usecase.login_register.RegisterUserUseCase
import com.example.movie.domain.usecase.login_register.UserProfileUseCase
import com.example.movie.presentation.viewmodel.AuthViewModel


class RegisterFragment :  BaseFragment<FragmentRegisterBinding>(FragmentRegisterBinding::inflate) {

    private val viewModel: AuthViewModel by activityViewModels {
        CommonViewModelFactory {
            AuthViewModel(
                RegisterUserUseCase(AuthRepositoryImpl(AuthRemoteDataSourceImpl(RetrofitInstance.authApiService),)),
                LoginUserUseCase(AuthRepositoryImpl(AuthRemoteDataSourceImpl(RetrofitInstance.authApiService),)),
                RefreshTokenUseCase(AuthRepositoryImpl(AuthRemoteDataSourceImpl(RetrofitInstance.authApiService),)),
                UserProfileUseCase(AuthRepositoryImpl(AuthRemoteDataSourceImpl(RetrofitInstance.authApiService),)),

            )
        }
    }
    override fun setupCreatedUI(){
        binding.phoneNumber.addPhoneNumberFormatting()
        binding.email.addEmailFormatting()
        navigateTo(binding.backReturn, R.id.action_registerFragment2_to_loginFragment)
        navigateTo(binding.registerButton, R.id.action_registerFragment2_to_loginFragment)

        val emailInput = binding.email
        val passwordInput = binding.password
        val nameInput = binding.username
        val registerButton = binding.registerButton

        registerButton.setOnClickListener {
            val user = User(
                email = emailInput.text.toString(),
                password = passwordInput.text.toString(),
                name = nameInput.text.toString(),
                avatar = "https://picsum.photos/800"
            )
            viewModel.register(user)
        }

        viewModel.isRegistered.observe(viewLifecycleOwner) { isRegistered ->
            if (isRegistered) {
                Toast.makeText(context, "Registration Successful", Toast.LENGTH_SHORT).show()
                findNavController().navigate(R.id.action_registerFragment2_to_loginFragment)
            }
        }

        viewModel.errorMessage.observe(viewLifecycleOwner) { message ->
            Toast.makeText(context, message, Toast.LENGTH_SHORT).show()
        }
    }




}