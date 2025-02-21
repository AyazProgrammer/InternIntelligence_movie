package com.example.movie.domain.usecase.search

import com.example.movie.domain.model.movie.MovieResult
import com.example.movie.domain.repository.MovieRepository

class SearchMovieUseCase(private val repository: MovieRepository){
    suspend operator fun invoke(query:String?=null,page: Int? = null): MovieResult {
        val currentPage = page ?: 1
        var results:MovieResult
        if (query.isNullOrBlank()){
             results = repository.getAllMovies(currentPage)
        }else{
             results  = repository.searchMovies(query,currentPage)
        }
        return MovieResult(
            page = results.page,
            results = results.results.filter { !it.posterPath.isNullOrBlank() },
            total_pages = results.total_pages,
            total_results = results.total_results
        )

    }
}