package com.example.movie.data.local.data_source

import com.example.movie.data.local.dao.MovieDao
import com.example.movie.data.local.entity.MovieEntity

class MovieLocalDataSourceImpl(private val movieDao: MovieDao):MovieLocalDataSource {
    override suspend fun saveEntities(entities: List<MovieEntity>) {
        movieDao.insertMovies(entities)
    }

    override suspend fun saveEntity(entity: MovieEntity) {
        movieDao.insertMovie(entity)
    }

    override suspend fun checkMovieEntity(id: Int):Boolean {
        return movieDao.isMovieExists(id)
    }

    /*  override suspend fun getEntities(page: Int): List<MovieEntity> {
          return movieDao.getAllMovies()
      }*/

    override suspend fun getEntities(): List<MovieEntity> {
        return movieDao.getAllMovies()
    }

    override suspend fun deleteEntity(entity: MovieEntity) {
       return movieDao.deleteMovie(entity)
    }
}