package com.example.movie.data.local.data_source

import com.example.movie.data.local.dao.GenreDao
import com.example.movie.data.local.dao.MovieDao
import com.example.movie.data.local.entity.GenreEntity

class GenreLocalDataSourceImpl(private val genreDao: GenreDao):GenreLocalDataSource {
    override suspend fun getEntities(): List<GenreEntity> {
        return genreDao.getAllGenres()
    }

    override suspend fun saveEntities(entities: List<GenreEntity>) {
        genreDao.insertGenres(entities)
    }
}