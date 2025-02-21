package com.example.movie.domain.repository

import com.example.movie.common.base.repository.Repository
import com.example.movie.domain.model.actor_detail.ActorDetailResult
import com.example.movie.domain.model.actors_movies.ActorResult

interface ActorRepository: Repository<ActorResult> {
    suspend fun getActors(page:Int):ActorResult
    suspend fun searchActor(query:String,page: Int):ActorResult
    suspend fun detailForActor(id: Int):ActorDetailResult
}