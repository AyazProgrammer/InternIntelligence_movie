package com.example.movie.domain.usecase.login_register

import com.example.movie.data.remote.model.request.login.AuthTokens
import com.example.movie.domain.repository.AuthRepository

class RefreshTokenUseCase(private val authRepository: AuthRepository) {
    suspend fun execute(refreshToken: String): AuthTokens = authRepository.refreshToken(refreshToken)
}