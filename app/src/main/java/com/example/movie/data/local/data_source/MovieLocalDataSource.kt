package com.example.movie.data.local.data_source

import com.example.movie.common.base.data_source.BaseDataSource
import com.example.movie.data.base.BaseLocalDataSource
import com.example.movie.data.local.entity.MovieEntity

interface MovieLocalDataSource: BaseLocalDataSource<MovieEntity> {


   suspend fun deleteEntity(entity:MovieEntity)

   suspend fun saveEntity(entity:MovieEntity)

   suspend fun checkMovieEntity(id: Int):Boolean




}