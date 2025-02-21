package com.example.movie.data.mapper.genre

import com.example.movie.common.base.mapper.BaseMapper
import com.example.movie.data.local.entity.GenreEntity
import com.example.movie.data.remote.model.entity.genre.GenreRemote
import com.example.movie.domain.model.genre.Genre

object GenreMapper: BaseMapper<GenreRemote, Genre, GenreEntity> {
    override fun GenreRemote.toDomain(): Genre {
        return Genre(
            id = this.id,
            name = this.name
        )
    }

    override fun GenreRemote.toEntity(): GenreEntity {
        return GenreEntity(
            id=this.id,
            name =this.name
        )
    }

    override fun GenreEntity.toEntityToDomain(): Genre {
        return Genre(
            id = this.id,
            name = this.name
        )
    }
}