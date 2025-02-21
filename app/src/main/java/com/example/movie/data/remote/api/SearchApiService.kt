package com.example.movie.data.remote.api

import com.example.movie.data.remote.model.response.movie.MovieResponse
import retrofit2.http.GET
import retrofit2.http.Query
import retrofit2.http.Url

interface SearchApiService {
    @GET
    suspend fun getFilteredMovies(
        @Url endpoint: String,
        @Query("sort_by") sortBy: String = "popularity.desc",
        @Query("vote_average.gte") voteAverage: Double = 7.5,
        @Query("with_genres") genres: List<Int>,
        @Query("primary_release_year") year: Int?,
        @Query("page") page: Int = 1
    ): MovieResponse
}