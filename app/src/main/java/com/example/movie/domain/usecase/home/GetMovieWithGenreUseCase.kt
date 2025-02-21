package com.example.movie.domain.usecase.home

import com.example.movie.domain.model.movie.Movie
import com.example.movie.domain.model.movie.MovieResult
import com.example.movie.domain.repository.MovieRepository

class GetMovieWithGenreUseCase(private val repository: MovieRepository) {
    suspend operator fun invoke(genre: Int? = null, page: Int? = null):MovieResult {
        val currentPage = page ?: 1
        return if (genre != null) {
            repository.getMovieForGenre(genre, currentPage)
        } else {
            repository.getNowPlayingMovies(currentPage)
        }
    }
}
