package com.example.movie.data.remote.data_source.filter

import android.util.Log
import com.example.movie.data.remote.api.MovieApiService
import com.example.movie.data.remote.api.SearchApiService
import com.example.movie.data.remote.model.filter.FilterRequest
import com.example.movie.data.remote.model.response.movie.MovieResponse

class FilterMovieDataSourceImpl(private val apiService: SearchApiService):FilterMovieDataSource {
    override suspend fun allFilteredMovies(param: FilterRequest, page: Int): MovieResponse {
        val endPoint = "discover/movie"
        Log.e("dataSourceFilter","${param.with_genres}")
        return apiService.getFilteredMovies(
            endpoint = endPoint,
            voteAverage = param.vote_average_gte.toDouble(),
            page = page,
            genres = param.with_genres,
            year = param.year?.toInt()
        )
    }
}