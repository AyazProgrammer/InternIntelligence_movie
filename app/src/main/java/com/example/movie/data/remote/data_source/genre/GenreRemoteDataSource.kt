package com.example.movie.data.remote.data_source.genre

import com.example.movie.data.base.BaseRemoteDataSource
import com.example.movie.data.remote.model.entity.genre.GenreRemote

interface GenreRemoteDataSource: BaseRemoteDataSource<GenreRemote> {
    suspend fun getEntities(): List<GenreRemote>
}