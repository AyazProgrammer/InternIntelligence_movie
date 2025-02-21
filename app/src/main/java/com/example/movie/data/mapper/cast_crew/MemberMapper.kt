package com.example.movie.data.mapper.cast_crew

import com.example.movie.common.base.mapper.BaseMapper
import com.example.movie.data.remote.model.entity.movie_detail.CastMemberRemote
import com.example.movie.data.remote.model.entity.movie_detail.CrewMemberRemote
import com.example.movie.data.remote.model.response.movie_detail.MovieCreditsResponse
import com.example.movie.domain.model.cast_crew.CastMember
import com.example.movie.domain.model.cast_crew.Credits
import com.example.movie.domain.model.cast_crew.CrewMember

object MemberMapper:BaseMapper<MovieCreditsResponse, Credits,Nothing> {
    override fun MovieCreditsResponse.toDomain(): Credits {
        return  Credits(
            id = this.id,
            cast = this.cast.toDomainCasts(),
            crew = this.crew.toDomainCrews() ,
        )
    }

    fun List<CastMemberRemote>.toDomainCasts(): List<CastMember> {
        return this.map { castMemberRemote ->
            CastMember(
                adult = castMemberRemote.adult,
                gender = castMemberRemote.gender,
                id = castMemberRemote.id,
                name = castMemberRemote.name,
                original_name = castMemberRemote.original_name,
                popularity = castMemberRemote.popularity,
                profile_path = castMemberRemote.profile_path,
                cast_id = castMemberRemote.cast_id,
                character = castMemberRemote.character,
                credit_id = castMemberRemote.credit_id,
                order = castMemberRemote.order
            )
        }
    }

    fun List<CrewMemberRemote>.toDomainCrews(): List<CrewMember> {
        return this.map { crewMemberRemote ->
            CrewMember(
                adult = crewMemberRemote.adult,
                gender = crewMemberRemote.gender,
                id = crewMemberRemote.id,
                name = crewMemberRemote.name,
                original_name = crewMemberRemote.original_name,
                popularity = crewMemberRemote.popularity,
                profile_path = crewMemberRemote.profile_path,
                credit_id = crewMemberRemote.credit_id,
                job = crewMemberRemote.job,
            )
        }
    }

}