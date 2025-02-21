package com.example.movie.data.mapper

import com.example.movie.common.base.mapper.BaseMapper
import com.example.movie.data.remote.model.entity.movie_detail.CastMemberRemote
import com.example.movie.domain.model.cast_crew.CastMember

object ActorMapper:BaseMapper<CastMemberRemote, CastMember,Nothing> {
    override fun CastMemberRemote.toDomain(): CastMember {
        return CastMember(
            adult = this.adult,
            gender = this.gender,
            id = this.id,
            name = this.name,
            original_name = this.original_name,
            popularity = this.popularity,
            profile_path = this.profile_path,
            cast_id = this.cast_id,
            character = this.character,
            credit_id = this.credit_id,
            order = this.order
        )
    }
}