package com.example.movie.data.local.data_source

import com.example.movie.common.base.data_source.BaseDataSource
import com.example.movie.data.base.BaseLocalDataSource
import com.example.movie.data.local.entity.GenreEntity
import com.example.movie.data.local.entity.MovieEntity
import com.example.movie.data.remote.model.entity.genre.GenreRemote

interface GenreLocalDataSource: BaseLocalDataSource<GenreEntity> {

}
