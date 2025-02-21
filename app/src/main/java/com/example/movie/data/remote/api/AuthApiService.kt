package com.example.movie.data.remote.api

import com.example.movie.data.remote.model.request.login.AuthTokens
import com.example.movie.data.remote.model.request.register.User
import com.example.movie.data.remote.model.response.profile.UserProfile
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.POST

interface AuthApiService {
    @POST("users/")
    suspend fun registerUser(@Body user: User): Response<Unit>

    @POST("auth/login")
    suspend fun loginUser(@Body credentials: Map<String, String>): Response<AuthTokens>

    @POST("auth/refresh-token")
    suspend fun refreshToken(@Body token: Map<String, String>): Response<AuthTokens>
    @GET("auth/profile")
    suspend fun getCurrentUser(@Header("Authorization") token: String): UserProfile
}
