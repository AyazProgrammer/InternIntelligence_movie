package com.example.movie.domain.model.review

data class Review(
    val id: Int,
    val page: Int,
    val results: List<UserReview>,
    val total_pages: Int,
    val total_results: Int
)
