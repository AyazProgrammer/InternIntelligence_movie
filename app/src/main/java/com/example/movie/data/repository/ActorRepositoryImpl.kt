package com.example.movie.data.repository

import android.util.Log
import com.example.movie.data.mapper.actors.ActorMapper.toDomain
import com.example.movie.data.mapper.detail_actor.DetailActorMapper.toDomain
import com.example.movie.data.remote.data_source.actor.ActorRemoteDataSource
import com.example.movie.data.remote.data_source.auth.AuthRemoteDataSource
import com.example.movie.domain.model.actor_detail.ActorDetailResult
import com.example.movie.domain.model.actors_movies.ActorResult
import com.example.movie.domain.repository.ActorRepository

class ActorRepositoryImpl(private val actorRemoteDataSource: ActorRemoteDataSource):ActorRepository{
    override suspend fun getActors(page: Int): ActorResult {
        Log.e("SSSSSSSSSSSSSSs","${actorRemoteDataSource.getAllActors(page).toDomain()}")
        return actorRemoteDataSource.getAllActors(page).toDomain()
    }

    override suspend fun searchActor(query: String, page: Int): ActorResult {
        return actorRemoteDataSource.searchAllActors(query,page).toDomain()
    }

    override suspend fun detailForActor(id: Int): ActorDetailResult {
        return actorRemoteDataSource.getActorDetail(id).toDomain()
    }

    override suspend fun getList(): List<ActorResult> {
        TODO("Not yet implemented")
    }
}