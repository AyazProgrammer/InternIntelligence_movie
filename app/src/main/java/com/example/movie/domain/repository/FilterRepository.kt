package com.example.movie.domain.repository

import com.example.movie.common.base.repository.Repository
import com.example.movie.data.remote.model.response.movie.MovieResponse
import com.example.movie.domain.model.filter.FilterItem
import com.example.movie.domain.model.movie.Movie
import com.example.movie.domain.model.movie.MovieResult

interface FilterRepository:Repository<Movie> {
    suspend fun getAllFilteredMovies(param: FilterItem, page: Int): MovieResult
}