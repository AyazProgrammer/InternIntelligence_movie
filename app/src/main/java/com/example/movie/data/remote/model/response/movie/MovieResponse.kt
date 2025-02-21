package com.example.movie.data.remote.model.response.movie

import com.example.movie.data.remote.model.entity.movie.MovieRemote

data class MovieResponse(
    val page: Int,
    val results: List<MovieRemote>,
    val total_pages: Int,
    val total_results: Int
)