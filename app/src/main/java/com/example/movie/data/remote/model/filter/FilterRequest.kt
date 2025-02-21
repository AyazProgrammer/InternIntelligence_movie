package com.example.movie.data.remote.model.filter

data class FilterRequest(
    val with_genres: List<Int>,
    val vote_average_gte: Float=0.0f,
    val year: String?
)