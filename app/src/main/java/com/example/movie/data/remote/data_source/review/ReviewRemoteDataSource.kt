package com.example.movie.data.remote.data_source.review

import com.example.movie.data.base.BaseRemoteDataSource
import com.example.movie.data.remote.model.response.movie_detail.ReviewsResponse

interface ReviewRemoteDataSource: BaseRemoteDataSource<ReviewsResponse> {
    suspend fun getMoviesReviews(movieId:Int): ReviewsResponse
}