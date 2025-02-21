package com.example.movie.data.remote.model.response.movie_detail

import com.example.movie.data.remote.model.entity.movie_detail.CastMemberRemote
import com.example.movie.data.remote.model.entity.movie_detail.CrewMemberRemote

data class MovieCreditsResponse(
    val id: Int,
    val cast: List<CastMemberRemote>,
    val crew: List<CrewMemberRemote>
)