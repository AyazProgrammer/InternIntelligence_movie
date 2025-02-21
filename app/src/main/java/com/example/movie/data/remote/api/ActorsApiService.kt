package com.example.movie.data.remote.api

import com.example.movie.data.remote.model.response.actor.ActorResponse
import com.example.movie.data.remote.model.response.actor_detail.ActorDetailResponse
import retrofit2.http.GET
import retrofit2.http.Query
import retrofit2.http.Url

interface ActorsApiService {

    @GET
    suspend fun getAllActors(
        @Url endpoint: String,
        @Query("page") page: Int
    ): ActorResponse

    @GET
    suspend fun searchAllActors(
        @Url endpoint: String,
        @Query("query") query: String = "",
        @Query("page") page: Int = 1,
    ):ActorResponse

    @GET
    suspend fun getActorDetail(
        @Url endpoint: String,
        @Query("append_to_response") appendToResponse: String = "movie_credits",
    ):ActorDetailResponse
}