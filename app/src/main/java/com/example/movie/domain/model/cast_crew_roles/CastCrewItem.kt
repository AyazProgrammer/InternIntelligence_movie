package com.example.movie.domain.model.cast_crew_roles

data class CastCrewItem(
    val id:String,
    val title: String?,
    val role: String?,
    val year: String?,
    val voteAverage: String?,
    val posterPath: String?
)