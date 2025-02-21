package com.example.movie.data.remote.api

import com.example.movie.data.remote.model.response.movie_detail.DetailMovieResponse
import com.example.movie.data.remote.model.response.movie_detail.MovieCreditsResponse
import com.example.movie.data.remote.model.response.movie.MovieResponse
import com.example.movie.data.remote.model.response.movie_detail.VideoResponse
import retrofit2.http.GET
import retrofit2.http.Query
import retrofit2.http.Url


interface MovieApiService {
    @GET
    suspend fun getMovies(@Url endpoint: String, @Query("page") page: Int): MovieResponse

    @GET
    suspend fun getMoviesforGenre(
        @Url endpoint: String,
        @Query("with_genres") genreId: Int,
        @Query("page") page: Int
    ): MovieResponse

    @GET
    suspend fun getMoviesWithDetail(
        @Url endpoint: String,
    ): DetailMovieResponse

    @GET
    suspend fun getSimilarMovies(
        @Url endpoint: String,
        @Query("page") page: Int
    ): MovieResponse

    @GET
    suspend fun getMovieVideos(
        @Url endpoint: String,
    ): VideoResponse
    @GET
    suspend fun getAllVideo(
        @Url endpoint: String,
        @Query("page") page: Int
    ):MovieResponse

    @GET
    suspend fun searchVideo(
        @Url endpoint: String,
        @Query("query") query: String,
        @Query("page") page: Int
    ):MovieResponse



    @GET
    suspend fun getWorker(
        @Url endpoint: String,
    ): MovieCreditsResponse
}