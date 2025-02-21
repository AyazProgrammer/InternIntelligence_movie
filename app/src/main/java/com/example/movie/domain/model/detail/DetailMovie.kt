package com.example.movie.domain.model.detail

import com.example.movie.common.base.model.Media
import com.example.movie.domain.model.genre.Genre

data class DetailMovie(
    val id: Int,
    val origin_country: List<String>,
    val adult: Boolean,
    val popularity: Double,
    val genres: List<Genre>,
    val backdrop_path: String?,
    val poster_path: String?,
    val status: String,
    val runtime:Int,
    val original_language: String,
    val spoken_languages: List<Language>,
    val original_title: String,
    val release_date: String,
    val vote_average: Double,
    val tagline: String?,
    val vote_count: Int,
    val title: String,
    val video: Boolean,
    val overview: String,
):Media(){
    val posterUrl: String
        get() = "$photoUrl$poster_path"
}


//val belongs_to_collection: Any?,
//val budget: Int,
//val homepage: String?,
//
//val imdb_id: String?,
//
//
//
//
//
//
//val revenue: Int,
//val runtime: Int,
//
//
//val tagline: String?,


