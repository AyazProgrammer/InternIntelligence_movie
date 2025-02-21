package com.example.movie.data.repository

import com.example.movie.data.remote.data_source.auth.AuthRemoteDataSource
import com.example.movie.data.remote.model.request.login.AuthTokens
import com.example.movie.data.remote.model.request.register.User
import com.example.movie.data.remote.model.response.profile.UserProfile
import com.example.movie.domain.repository.AuthRepository

class AuthRepositoryImpl(private val authRemoteDataSource: AuthRemoteDataSource) : AuthRepository {

    override suspend fun register(user: User): Boolean {
        return authRemoteDataSource.register(user)
    }

    override suspend fun login(email: String, password: String): AuthTokens {
        return authRemoteDataSource.login(email, password)
    }

    override suspend fun getUserProfile(accessToken: String): UserProfile {
       return authRemoteDataSource.getUser(accessToken)
    }

    override suspend fun refreshToken(refreshToken: String): AuthTokens {
        return authRemoteDataSource.refreshToken(refreshToken)
    }
}