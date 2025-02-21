package com.example.movie.data.repository

import android.util.Log
import com.example.movie.data.local.data_source.GenreLocalDataSource
import com.example.movie.data.mapper.genre.GenreMapper.toDomain
import com.example.movie.data.mapper.genre.GenreMapper.toEntity
import com.example.movie.data.mapper.genre.GenreMapper.toEntityToDomain
import com.example.movie.data.remote.data_source.genre.GenreRemoteDataSource
import com.example.movie.domain.model.genre.Genre
import com.example.movie.domain.repository.GenreRepository

class GenreRepositoryImpl(
    private val remoteDataSource: GenreRemoteDataSource,
    private val localDataSource: GenreLocalDataSource
):GenreRepository {
    override suspend fun getList(): List<Genre> {
        return try {
            remoteDataSource.getEntities().takeIf { it.isNotEmpty() }?.let { it ->
                localDataSource.saveEntities(it.map { it.toEntity() })
            }
            Log.e("local genre data source", "${remoteDataSource.getEntities().map { it.toDomain() }}")
            localDataSource.getEntities().map { it.toEntityToDomain() }
        } catch (e: Exception) {
            localDataSource.getEntities().map { it.toEntityToDomain() }
        }
    }
}