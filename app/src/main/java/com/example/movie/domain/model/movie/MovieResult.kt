package com.example.movie.domain.model.movie

import com.example.movie.data.remote.model.entity.movie.MovieRemote

data class MovieResult(
    val page: Int,
    val results: List<Movie>,
    val total_pages: Int,
    val total_results: Int
)
