package com.example.movie.domain.usecase.favorites

import com.example.movie.domain.model.genre.Genre
import com.example.movie.domain.model.movie.Movie
import com.example.movie.domain.repository.GenreRepository
import com.example.movie.domain.repository.MovieRepository

class DeleteFavMovieUseCase (private val repository: MovieRepository) {
    suspend operator fun invoke(movie: Movie) {
        return repository.deleteMovie(movie)

    }
}