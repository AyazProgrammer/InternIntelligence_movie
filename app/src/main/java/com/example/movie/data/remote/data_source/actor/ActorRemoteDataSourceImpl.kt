package com.example.movie.data.remote.data_source.actor

import com.example.movie.data.remote.api.ActorsApiService
import com.example.movie.data.remote.model.response.actor.ActorResponse
import com.example.movie.data.remote.model.response.actor_detail.ActorDetailResponse

class ActorRemoteDataSourceImpl(private val apiService: ActorsApiService):ActorRemoteDataSource {
    override suspend fun getAllActors(page: Int): ActorResponse {
        val endPoint = "person/popular"

        return apiService.getAllActors(endPoint,page)
    }

    override suspend fun searchAllActors(query: String, page: Int): ActorResponse {
        val endPoint = "search/person"
        val apiKey = "150eda341214de29f8d54f22f4b555ae"
        return  apiService.searchAllActors(endPoint,query, page)
    }

    override suspend fun getActorDetail(id: Int): ActorDetailResponse {
        val endPoint = "person/{actorId}"
        val finalEndPoint = endPoint.replace("{actorId}", id.toString())
        return apiService.getActorDetail(finalEndPoint)
    }
}