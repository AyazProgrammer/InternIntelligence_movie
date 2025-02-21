package com.example.movie.data.remote.data_source.filter

import com.example.movie.data.remote.model.filter.FilterRequest
import com.example.movie.data.remote.model.response.movie.MovieResponse

interface FilterMovieDataSource {
    suspend fun allFilteredMovies(param: FilterRequest, page:Int): MovieResponse
}