package com.example.movie.data.remote.data_source.genre

import com.example.movie.data.remote.api.GenreApiService
import com.example.movie.data.remote.model.entity.genre.GenreRemote

class GenreRemoteDataSourceImpl(private val apiService: GenreApiService) : GenreRemoteDataSource {


    override suspend fun getEntities(): List<GenreRemote> {
        return apiService.getGenres().genres
    }
}