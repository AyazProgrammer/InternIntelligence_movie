package com.example.movie.data.remote.api

import com.example.movie.data.remote.model.response.movie_detail.ReviewsResponse
import retrofit2.http.GET
import retrofit2.http.Url

interface ReviewApiService {
    @GET
    suspend fun getReviews(
        @Url endpoint: String,
    ): ReviewsResponse
}