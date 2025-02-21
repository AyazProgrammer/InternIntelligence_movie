package com.example.movie.data.mapper.actors

import com.example.movie.common.base.mapper.BaseMapper
import com.example.movie.data.remote.model.entity.actor.ActorRemote
import com.example.movie.data.remote.model.entity.actor.KnownFor
import com.example.movie.data.remote.model.response.actor.ActorResponse
import com.example.movie.domain.model.actors_movies.Actor
import com.example.movie.domain.model.actors_movies.ActorResult
import com.example.movie.domain.model.movie.Movie

object ActorMapper:BaseMapper<ActorResponse,ActorResult,Nothing> {
    override fun ActorResponse.toDomain(): ActorResult {
        return ActorResult(
            page = this.page,
            results = this.results.actorToDomain(),
            totalPages = this.total_pages,
            totalResults = this.total_results
        )
    }

     private fun List<ActorRemote>.actorToDomain(): List<Actor> {
         return this.map { ActorRemote ->
             Actor(
                 id = ActorRemote.id,
                 name = ActorRemote.name,
                 originalName = ActorRemote.original_name,
                 popularity = ActorRemote.popularity,
                 profilePath = ActorRemote.profile_path,
                 knownFor = ActorRemote.known_for.toDomainMovies(),
                 knownForDepartment = ActorRemote.known_for_department
             )
         }

     }
    fun List<KnownFor>.toDomainMovies(): List<Movie> {
        return this.map { MovieRemote ->
            Movie(
                title = MovieRemote.title ?: "Default Title",
                overview = MovieRemote.overview,
                posterPath = MovieRemote.poster_path,
                releaseDate = MovieRemote.release_date?:"",
                voteAverage = MovieRemote.vote_average.toString(),
                voteCount = MovieRemote.vote_count.toString(),
                id = MovieRemote.id
            )
        }
    }
}