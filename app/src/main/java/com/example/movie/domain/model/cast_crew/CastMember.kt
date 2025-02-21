package com.example.movie.domain.model.cast_crew

import com.example.movie.common.base.model.Media

data class CastMember(
    val adult: Boolean,
    val gender: Int,
    val id: Int,
    val name: String,
    val original_name: String,
    val popularity: Double,
    val profile_path: String?,
    val cast_id: Int,
    val character: String,
    val credit_id: String,
    val order: Int
):Media(){
    val profileUrl: String
        get() = "$photoUrl$profile_path"
}
