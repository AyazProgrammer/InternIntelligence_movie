package com.example.movie.domain.model.review

data class UserReview (
    val author: String,
    val author_details: AuthorDetail,
    val content: String,
    val created_at: String,
    val id: String,
    val updated_at: String,
    val url: String
)