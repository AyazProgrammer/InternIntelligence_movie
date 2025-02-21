package com.example.movie.data.remote.model.response.movie_detail

import com.example.movie.data.remote.model.entity.review.UserReviewRemote

data class ReviewsResponse(
    val id: Int,
    val page: Int,
    val results: List<UserReviewRemote>,
    val total_pages: Int,
    val total_results: Int
)