package com.example.movie.data.remote.model.response.movie_detail

import com.example.movie.data.remote.model.entity.genre.GenreRemote
import com.example.movie.data.remote.model.entity.movie_detail.ProductionCountryRemote
import com.example.movie.data.remote.model.entity.movie_detail.SpokenLanguageRemote

data class DetailMovieResponse(
    val adult: Boolean,
    val backdrop_path: String?,
    val belongs_to_collection: Any?,
    val budget: Int,
    val genres: List<GenreRemote>,
    val homepage: String?,
    val id: Int,
    val imdb_id: String?,
    val origin_country: List<String>,
    val original_language: String,
    val original_title: String,
    val overview: String,
    val popularity: Double,
    val poster_path: String?,
    val production_companies: List<ProductionCountryRemote>,
    val production_countries: List<ProductionCountryRemote>,
    val release_date: String,
    val revenue: Int,
    val runtime: Int,
    val spoken_languages: List<SpokenLanguageRemote>,
    val status: String,
    val tagline: String?,
    val title: String,
    val video: Boolean,
    val vote_average: Double,
    val vote_count: Int
)