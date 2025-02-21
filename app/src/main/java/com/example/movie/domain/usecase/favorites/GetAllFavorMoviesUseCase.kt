package com.example.movie.domain.usecase.favorites

import com.example.movie.domain.model.movie.Movie
import com.example.movie.domain.repository.MovieRepository

class GetAllFavorMoviesUseCase (private val repository: MovieRepository) {
    suspend operator fun invoke():List<Movie> {

        return repository.getAllMovieEntity()

    }
}