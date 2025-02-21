package com.example.movie.domain.usecase.login_register

import com.example.movie.data.remote.model.request.register.User
import com.example.movie.domain.repository.AuthRepository

class RegisterUserUseCase(private val authRepository: AuthRepository) {
    suspend fun execute(user: User): Boolean = authRepository.register(user)
}