package com.example.movie.domain.model.filter

data class FilterItem(
    val genres: List<Int> = emptyList(),
    val year: String? = null,
    val imdbRating: Float = 0.0f
)
