package com.example.movie.domain.model.cast_crew

data class Credits (
    val id: Int,
    val cast: List<CastMember>,
    val crew: List<CrewMember>
)