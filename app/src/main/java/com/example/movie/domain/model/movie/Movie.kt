package com.example.movie.domain.model.movie

import com.example.movie.common.base.model.Media


data class Movie(
    val id: Int,
    val title: String,
    val overview: String,
    val posterPath: String?,
    val releaseDate: String,
    val voteAverage:String,
    val voteCount:String
):Media(){
    val posterUrl: String
        get() = "$photoUrl$posterPath"
}
