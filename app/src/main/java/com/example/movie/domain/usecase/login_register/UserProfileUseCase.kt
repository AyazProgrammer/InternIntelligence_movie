package com.example.movie.domain.usecase.login_register

import com.example.movie.data.remote.model.response.profile.UserProfile
import com.example.movie.domain.repository.AuthRepository

class UserProfileUseCase (private val authRepository: AuthRepository) {
    suspend fun execute(accessToken:String): UserProfile =  authRepository.getUserProfile(accessToken)
}