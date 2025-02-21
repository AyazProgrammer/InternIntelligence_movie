package com.example.movie.data.repository

import android.util.Log
import com.example.movie.data.mapper.filter.FilterItemMapper.toRequestModel
import com.example.movie.data.mapper.movie.MovieResultMapper.toDomain
import com.example.movie.data.remote.data_source.filter.FilterMovieDataSource
import com.example.movie.data.remote.data_source.movie.MovieRemoteDataSource
import com.example.movie.domain.model.filter.FilterItem
import com.example.movie.domain.model.movie.Movie
import com.example.movie.domain.model.movie.MovieResult
import com.example.movie.domain.repository.FilterRepository

class FilterRepositoryImpl(private val remoteDataSource: FilterMovieDataSource):FilterRepository {
    override suspend fun getAllFilteredMovies(param: FilterItem, page: Int): MovieResult {
        Log.e("RepoFilter","${param.genres}")
        return remoteDataSource.allFilteredMovies(param.toRequestModel(),page).toDomain()
    }

    override suspend fun getList(): List<Movie> {
        TODO("Not yet implemented")
    }
}