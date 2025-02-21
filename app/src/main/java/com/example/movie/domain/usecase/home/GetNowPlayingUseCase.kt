package com.example.movie.domain.usecase.home

import com.example.movie.domain.model.movie.Movie
import com.example.movie.domain.model.movie.MovieResult
import com.example.movie.domain.repository.MovieRepository

class GetNowPlayingUseCase(private val repository: MovieRepository) {
    suspend operator fun invoke(page: Int? = null):MovieResult {
        return if (page != null) {
            repository.getNowPlayingMovies(page)

        } else {
            repository.getNowPlayingMovies(1)
        }

    }
}