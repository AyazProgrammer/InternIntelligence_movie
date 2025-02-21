package com.example.movie.data.remote.model.entity.movie

data class MovieRemote(
    var id: Int,
    val title: String,
    val overview: String,
    val original_title:String,
    val poster_path: String?,
    val release_date: String,
    val vote_average:String,
    val vote_count:String
)
