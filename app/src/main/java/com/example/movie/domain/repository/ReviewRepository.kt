package com.example.movie.domain.repository

import com.example.movie.common.base.repository.Repository
import com.example.movie.domain.model.review.Review

interface ReviewRepository:Repository<Review> {
    suspend fun getReview(movieId:Int):Review
}