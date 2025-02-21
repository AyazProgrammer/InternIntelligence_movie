package com.example.movie.data.remote.api

import com.example.movie.data.remote.model.response.genre.GenreResponse
import retrofit2.http.GET

interface GenreApiService {
    @GET("genre/movie/list")
    suspend fun getGenres(): GenreResponse
}