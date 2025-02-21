package com.example.movie.domain.usecase.detail

import com.example.movie.domain.model.detail.DetailMovie
import com.example.movie.domain.repository.MovieRepository

class GetDetailMovieUseCase (private val repository: MovieRepository) {
    suspend operator fun invoke(movieId: Int): DetailMovie {

         return repository.getMovieWithDetail(movieId)

    }
}