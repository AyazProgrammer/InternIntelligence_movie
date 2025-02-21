package com.example.movie.data.base

import com.example.movie.common.base.data_source.BaseDataSource
import com.example.movie.data.local.entity.MovieEntity

interface BaseLocalDataSource<T>:BaseDataSource<T>{
    suspend fun saveEntities(entities: List<T>)

    suspend fun getEntities(): List<T>
}