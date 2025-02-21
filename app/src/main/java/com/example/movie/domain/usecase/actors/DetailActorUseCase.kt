package com.example.movie.domain.usecase.actors

import com.example.movie.domain.model.actor_detail.ActorDetailResult
import com.example.movie.domain.model.actors_movies.ActorResult
import com.example.movie.domain.repository.ActorRepository

class DetailActorUseCase (private val repository: ActorRepository) {
    suspend operator fun invoke(id: Int): ActorDetailResult {

        var data = repository.detailForActor(id)
        val cast = data.movie_credits.cast.filter { it.poster_path.isNotBlank() }
        val crew = data.movie_credits.crew.filter { !it.poster_path.isNullOrBlank() }
         data.movie_credits.cast = cast
         data.movie_credits.crew =crew
        return data

    }
}