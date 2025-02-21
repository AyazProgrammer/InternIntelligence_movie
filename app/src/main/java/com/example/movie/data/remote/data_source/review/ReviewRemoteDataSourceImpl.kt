package com.example.movie.data.remote.data_source.review

import com.example.movie.data.remote.api.ReviewApiService
import com.example.movie.data.remote.model.response.movie_detail.ReviewsResponse

class ReviewRemoteDataSourceImpl(private val apiService: ReviewApiService) : ReviewRemoteDataSource {
    override suspend fun getMoviesReviews(movieId: Int): ReviewsResponse {
        val endPoint = "movie/{movieId}/reviews"

        val finalEndPoint = endPoint.replace("{movieId}", movieId.toString())
        return apiService.getReviews(finalEndPoint)
    }
}