package com.example.movie.domain.usecase.detail

import com.example.movie.domain.model.movie.Movie
import com.example.movie.domain.model.movie.MovieResult
import com.example.movie.domain.repository.MovieRepository

class GetSimilarMoviesUseCase(private val repository: MovieRepository) {
    suspend operator fun invoke(movieId :Int, page: Int? = null): MovieResult {
        val currentPage = page ?: 1
        val results  = repository.getSimilarMovies(movieId,currentPage)
        return MovieResult(
            page = results.page,
            results = results.results.filter { !it.posterPath.isNullOrBlank() },
            total_pages = results.total_pages,
            total_results = results.total_results
        )

    }
}