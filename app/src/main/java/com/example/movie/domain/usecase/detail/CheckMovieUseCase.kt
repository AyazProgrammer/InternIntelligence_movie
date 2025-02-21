package com.example.movie.domain.usecase.detail

import com.example.movie.domain.model.movie.Movie
import com.example.movie.domain.repository.MovieRepository

class CheckMovieUseCase (private val repository: MovieRepository) {
    suspend operator fun invoke(id: Int):Boolean {
        return repository.checkMovieInDataBase(id)

    }
}