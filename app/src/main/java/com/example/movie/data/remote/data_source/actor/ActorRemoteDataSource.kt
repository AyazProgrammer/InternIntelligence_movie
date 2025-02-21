package com.example.movie.data.remote.data_source.actor

import com.example.movie.data.base.BaseRemoteDataSource
import com.example.movie.data.remote.model.response.actor.ActorResponse
import com.example.movie.data.remote.model.response.actor_detail.ActorDetailResponse

interface ActorRemoteDataSource: BaseRemoteDataSource<ActorResponse> {

    suspend fun getAllActors(page: Int): ActorResponse
    suspend fun searchAllActors(query:String,page:Int):ActorResponse
    suspend fun getActorDetail(id:Int):ActorDetailResponse
}