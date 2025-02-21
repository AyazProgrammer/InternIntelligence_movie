package com.example.movie.data.mapper.detail

import com.example.movie.common.base.mapper.BaseMapper
import com.example.movie.data.remote.model.entity.genre.GenreRemote
import com.example.movie.data.remote.model.entity.movie_detail.SpokenLanguageRemote
import com.example.movie.data.remote.model.response.movie_detail.DetailMovieResponse
import com.example.movie.domain.model.detail.DetailMovie
import com.example.movie.domain.model.genre.Genre
import com.example.movie.domain.model.detail.Language

object DetailMovieMapper: BaseMapper<DetailMovieResponse, DetailMovie, Nothing> {
    override fun DetailMovieResponse.toDomain(): DetailMovie {
        return DetailMovie(
            id = this.id,
            origin_country = this.origin_country,
            adult = this.adult,
            popularity = this.popularity,
            genres = this.genres.map { it.toDomain() },
            backdrop_path = this.backdrop_path,
            poster_path = this.poster_path,
            status = this.status,
            original_language = this.original_language,
            spoken_languages = this.spoken_languages.map { it.toDomain() },
            original_title = this.original_title,
            release_date = this.release_date,
            vote_average = this.vote_average,
            vote_count = this.vote_count,
            title = this.title,
            video = this.video,
            overview = this.overview,
            tagline = this.tagline,
            runtime = this.runtime
        )
    }
    fun GenreRemote.toDomain(): Genre {
        return Genre(id = this.id, name = this.name)
    }
    fun SpokenLanguageRemote.toDomain(): Language {
        return Language(
            english_name = this.english_name,
            name = this.name
        )
    }

    override fun Nothing.toEntityToDomain(): DetailMovie {
        TODO("Not yet implemented")
    }
}