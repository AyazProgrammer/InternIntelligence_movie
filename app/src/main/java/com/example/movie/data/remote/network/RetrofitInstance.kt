package com.example.movie.data.remote.network

import com.example.movie.common.constants.ApiConstants
import com.example.movie.data.remote.api.ActorsApiService
import com.example.movie.data.remote.api.AuthApiService
import com.example.movie.data.remote.api.GenreApiService
import com.example.movie.data.remote.api.MovieApiService
import com.example.movie.data.remote.api.ReviewApiService
import com.example.movie.data.remote.api.SearchApiService
import com.example.movie.data.remote.interceptor.ApiKeyInterceptor
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

object RetrofitInstance {

    private val loggingInterceptor = HttpLoggingInterceptor().apply {
        level = HttpLoggingInterceptor.Level.BODY
    }
    private val apiKeyInterceptor = ApiKeyInterceptor()

    private val client = OkHttpClient.Builder()
        .connectTimeout(30, TimeUnit.SECONDS)
        .readTimeout(30, TimeUnit.SECONDS)
        .writeTimeout(30, TimeUnit.SECONDS)
        .addInterceptor(loggingInterceptor)
        .addInterceptor(apiKeyInterceptor)
        .build()




    private val authClient = OkHttpClient.Builder()
        .connectTimeout(30, TimeUnit.SECONDS)
        .readTimeout(30, TimeUnit.SECONDS)
        .writeTimeout(30, TimeUnit.SECONDS)
        .addInterceptor(loggingInterceptor)
        .build()
    private val retrofit = Retrofit.Builder()
        .client(client)
        .baseUrl(ApiConstants.BASE_URL)
        .addConverterFactory(GsonConverterFactory.create())
        .build()



    private val authRetrofit = Retrofit.Builder()
        .client(authClient)
        .baseUrl("https://api.escuelajs.co/api/v1/")
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    val movieApiService: MovieApiService by lazy {
        retrofit.create(MovieApiService::class.java)
    }
    val reviewApiService: ReviewApiService by lazy {
        retrofit.create(ReviewApiService::class.java)
    }
    val genreApiService: GenreApiService by lazy {
        retrofit.create(GenreApiService::class.java)
    }
    val searchApiService: SearchApiService by lazy {
        retrofit.create(SearchApiService::class.java)
    }


    val actorsApiService: ActorsApiService by lazy {
        retrofit.create(ActorsApiService::class.java)
    }

    val authApiService: AuthApiService by lazy {
        authRetrofit.create(AuthApiService::class.java)
    }

}
