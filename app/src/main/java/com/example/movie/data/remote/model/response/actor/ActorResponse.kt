package com.example.movie.data.remote.model.response.actor

import com.example.movie.data.remote.model.entity.actor.ActorRemote

data class ActorResponse(
    val page: Int,
    val results: List<ActorRemote>,
    val total_pages: Int,
    val total_results: Int
)