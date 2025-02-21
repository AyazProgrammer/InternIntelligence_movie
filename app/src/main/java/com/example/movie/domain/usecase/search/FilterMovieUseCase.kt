package com.example.movie.domain.usecase.search

import com.example.movie.domain.model.filter.FilterItem
import com.example.movie.domain.model.movie.MovieResult
import com.example.movie.domain.repository.FilterRepository
import com.example.movie.domain.repository.MovieRepository

class FilterMovieUseCase(private val repository: FilterRepository) {

    suspend operator fun invoke(filter:FilterItem,page: Int? = null): MovieResult {
        val currentPage = page ?: 1

        val results  = repository.getAllFilteredMovies(filter,currentPage)
        return MovieResult(
            page = results.page,
            results = results.results.filter { !it.posterPath.isNullOrBlank() },
            total_pages = results.total_pages,
            total_results = results.total_results
        )

    }
}