package com.example.movie.data.repository

import com.example.movie.data.mapper.review.ReviewMapper.toDomain
import com.example.movie.data.remote.data_source.review.ReviewRemoteDataSource
import com.example.movie.domain.model.review.Review
import com.example.movie.domain.repository.ReviewRepository

class ReviewRepositoryImpl(private val remoteDataSource: ReviewRemoteDataSource):ReviewRepository {
    override suspend fun getReview(movieId: Int): Review {
        return  remoteDataSource.getMoviesReviews(movieId).toDomain()
    }

    override suspend fun getList(): List<Review> {
        TODO("Not yet implemented")
    }
}