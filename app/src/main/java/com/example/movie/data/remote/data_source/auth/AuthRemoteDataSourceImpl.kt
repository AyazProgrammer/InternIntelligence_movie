package com.example.movie.data.remote.data_source.auth

import android.util.Log
import com.example.movie.data.remote.api.AuthApiService
import com.example.movie.data.remote.model.request.login.AuthTokens
import com.example.movie.data.remote.model.request.register.User
import com.example.movie.data.remote.model.response.profile.UserProfile

class AuthRemoteDataSourceImpl(private val apiService: AuthApiService) : AuthRemoteDataSource {
    override suspend fun register(user: User): Boolean {
        val response = apiService.registerUser(user)
        Log.e("Auth", "Error: ${response.code()} - ${response.message()}")
        return response.isSuccessful
    }

    override suspend fun login(email: String, password: String): AuthTokens {
        val response = apiService.loginUser(mapOf("email" to email, "password" to password))
        if (response.isSuccessful) {
            return response.body()!!
        } else {
            throw Exception("Login failed")
        }
    }

    override suspend fun getUser(accessToken: String): UserProfile {
        return  apiService.getCurrentUser("Bearer $accessToken")
    }

    override suspend fun refreshToken(refreshToken: String): AuthTokens {
        val response = apiService.refreshToken(mapOf("refreshToken" to refreshToken))
        if (response.isSuccessful) {
            return response.body()!!
        } else {
            throw Exception("Token refresh failed")
        }
    }
}