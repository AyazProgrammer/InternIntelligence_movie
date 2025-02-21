package com.example.movie.data.mapper.detail_actor

import com.example.movie.common.base.mapper.BaseMapper
import com.example.movie.data.remote.model.entity.actor_detail.Cast
import com.example.movie.data.remote.model.entity.actor_detail.Crew
import com.example.movie.data.remote.model.entity.actor_detail.MovieCredits
import com.example.movie.data.remote.model.entity.movie.MovieRemote
import com.example.movie.data.remote.model.entity.movie_detail.CastMemberRemote
import com.example.movie.data.remote.model.response.actor_detail.ActorDetailResponse
import com.example.movie.domain.model.actor_detail.ActorDetailResult
import com.example.movie.domain.model.actor_detail.CastRole
import com.example.movie.domain.model.actor_detail.CreditRoles
import com.example.movie.domain.model.actor_detail.CrewRole
import com.example.movie.domain.model.cast_crew.CastMember
import com.example.movie.domain.model.cast_crew.Credits
import com.example.movie.domain.model.movie.Movie

object DetailActorMapper:BaseMapper<ActorDetailResponse,ActorDetailResult,Nothing> {
    override fun ActorDetailResponse.toDomain(): ActorDetailResult {
        return ActorDetailResult(
            adult = this.adult,
            also_known_as = this.also_known_as,
            biography = this.biography,
            birthday = this.birthday,
            deathday = this.deathday?.toString()?: "alive",
            gender = this.gender,
            homepage = this.homepage?.toString()?:"",
            id = this.id,
            imdb_id = this.imdb_id,
            known_for_department = this.known_for_department,
            movie_credits = this.movie_credits.toDomainCredits(),
            name = this.name,
            place_of_birth = this.place_of_birth,
            popularity = this.popularity,
            profile_path = this.profile_path?.toString()?:""
        )
    }

     fun MovieCredits.toDomainCredits(): CreditRoles {
        return CreditRoles(
            cast = this.cast.toDomainCasts(),
            crew = this.crew.toDomainCrews(),

        )
    }
    fun List<Cast>.toDomainCasts(): List<CastRole> {
        return this.map { Cast ->
            CastRole(
                adult = Cast.adult,
                character = Cast.character,
                credit_id = Cast.credit_id,
                id = Cast.id,
                order = Cast.order,
                original_language = Cast.original_language,
                original_title = Cast.original_title,
                overview = Cast.overview,
                popularity = Cast.popularity,
                poster_path = Cast.poster_path?.toString()?:"",
                release_date = Cast.release_date,
                title = Cast.title,
                video = Cast.video,
                vote_average = Cast.vote_average,
                vote_count = Cast.vote_count,
                backdrop_path = Cast.backdrop_path,
                genre_ids = Cast.genre_ids
            )
        }
    }

    fun List<Crew>.toDomainCrews(): List<CrewRole>{
        return this.map { Crew ->
            CrewRole(
                adult = Crew.adult,
                credit_id = Crew.credit_id,
                id = Crew.id,
                original_language = Crew.original_language,
                original_title = Crew.original_title,
                overview = Crew.overview,
                popularity = Crew.popularity,
                poster_path = Crew.poster_path?.toString()?:"",
                release_date = Crew.release_date,
                title = Crew.title,
                video = Crew.video,
                vote_average = Crew.vote_average,
                vote_count = Crew.vote_count,
                backdrop_path = Crew.backdrop_path,
                genre_ids = Crew.genre_ids,
                department = Crew.department,
                job = Crew.job
            )
        }
    }
}