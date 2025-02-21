package com.example.movie.data.remote.model.entity.review

data class UserReviewRemote(
    val author: String,
    val author_details: AuthorDetailsRemote,
    val content: String,
    val created_at: String,
    val id: String,
    val updated_at: String,
    val url: String
)