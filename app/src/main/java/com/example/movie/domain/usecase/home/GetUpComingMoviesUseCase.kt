package com.example.movie.domain.usecase.home

import com.example.movie.domain.model.movie.Movie
import com.example.movie.domain.model.movie.MovieResult
import com.example.movie.domain.repository.MovieRepository

class GetUpComingMoviesUseCase (private val repository: MovieRepository) {
    suspend operator fun invoke(page: Int? = null): MovieResult {
        return if (page != null) {
            repository.getUpComingMovies(page)

        } else {
            repository.getUpComingMovies(1)
        }

    }
}