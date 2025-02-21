package com.example.movie.domain.model.actors_movies

import com.example.movie.data.remote.model.entity.actor.ActorRemote

data class ActorResult(
    val page: Int,
    val results: List<Actor>,
    val totalPages: Int,
    val totalResults: Int
)
