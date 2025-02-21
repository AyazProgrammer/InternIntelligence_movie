package com.example.movie.domain.usecase.login_register

import com.example.movie.data.remote.model.request.login.AuthTokens
import com.example.movie.domain.repository.AuthRepository

class LoginUserUseCase(private val authRepository: AuthRepository) {
    suspend fun execute(email: String, password: String): AuthTokens = authRepository.login(email, password)
}