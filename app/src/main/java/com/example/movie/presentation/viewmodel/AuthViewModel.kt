package com.example.movie.presentation.viewmodel

import android.util.Log
import androidx.lifecycle.MutableLiveData
import com.example.movie.common.base.viewmodel.BaseViewModel
import com.example.movie.data.remote.model.request.login.AuthTokens
import com.example.movie.data.remote.model.request.register.User
import com.example.movie.data.remote.model.response.profile.UserProfile
import com.example.movie.domain.usecase.login_register.LoginUserUseCase
import com.example.movie.domain.usecase.login_register.RefreshTokenUseCase
import com.example.movie.domain.usecase.login_register.RegisterUserUseCase
import com.example.movie.domain.usecase.login_register.UserProfileUseCase
import kotlinx.coroutines.launch

class AuthViewModel(
    private val registerUserUseCase: RegisterUserUseCase,
    private val loginUserUseCase: LoginUserUseCase,
    private val refreshTokenUseCase: RefreshTokenUseCase,
    private val userProfileUseCase: UserProfileUseCase
) :  BaseViewModel()  {

    val authTokens = MutableLiveData<AuthTokens>()
    val userProfile = MutableLiveData<UserProfile>()
    val isRegistered = MutableLiveData<Boolean>()
    override val errorMessage = MutableLiveData<String>()

    fun login(email: String, password: String) {
        viewModelScope.launch {
            try {
                authTokens.value = loginUserUseCase.execute(email, password)
                Log.e("viewmodel observe token","${authTokens.value}")
            } catch (e: Exception) {
                errorMessage.value = e.message
            }
        }
    }

    fun register(user: User) {
        viewModelScope.launch {
            try {
                isRegistered.value = registerUserUseCase.execute(user)
            } catch (e: Exception) {
                errorMessage.value = e.message
            }
        }
    }

    fun refreshToken(refreshToken: String) {
        viewModelScope.launch {
            try {
                authTokens.value = refreshTokenUseCase.execute(refreshToken)
            } catch (e: Exception) {
                errorMessage.value = e.message
            }
        }
    }
    fun getUserProfile(accessTokens: String) {
        viewModelScope.launch {
            try {
                userProfile.value = userProfileUseCase.execute(accessTokens)
                Log.d("user profile","${userProfile.value}")
            } catch (e: Exception) {
                errorMessage.value = e.message
            }
        }
    }
    fun deleteUserLogIn(){
        authTokens.value?.refresh_token=""
        authTokens.value?.access_token=""
    }
}