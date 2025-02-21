package com.example.movie.data.remote.data_source.auth

import com.example.movie.data.remote.model.request.login.AuthTokens
import com.example.movie.data.remote.model.request.register.User
import com.example.movie.data.remote.model.response.profile.UserProfile

interface AuthRemoteDataSource {
    suspend fun register(user: User): Boolean
    suspend fun login(email: String, password: String): AuthTokens
    suspend fun refreshToken(refreshToken: String): AuthTokens
    suspend fun getUser(accessToken: String):UserProfile
}