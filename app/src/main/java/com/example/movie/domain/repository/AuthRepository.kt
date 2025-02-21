package com.example.movie.domain.repository

import com.example.movie.data.remote.model.request.login.AuthTokens
import com.example.movie.data.remote.model.request.register.User
import com.example.movie.data.remote.model.response.profile.UserProfile

interface AuthRepository {
    suspend fun register(user: User): Boolean
    suspend fun login(email: String, password: String): AuthTokens
    suspend fun refreshToken(refreshToken: String): AuthTokens
    suspend fun getUserProfile(accessToken: String): UserProfile
}