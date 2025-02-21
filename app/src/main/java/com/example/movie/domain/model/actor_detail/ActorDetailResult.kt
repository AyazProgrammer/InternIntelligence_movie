package com.example.movie.domain.model.actor_detail

import com.example.movie.common.base.model.Media
import com.example.movie.data.remote.model.entity.actor_detail.MovieCredits
import com.example.movie.domain.model.cast_crew.Credits

data class ActorDetailResult(
    val adult: Boolean,
    val also_known_as: List<String>,
    val biography: String,
    val birthday: String,
    val deathday: String?,
    val gender: Int,
    val homepage: String? = "",
    val id: Int,
    val imdb_id: String,
    val known_for_department: String,
    val movie_credits: CreditRoles,
    val name: String,
    val place_of_birth: String,
    val popularity: Double,
    val profile_path: String = ""
):Media() {
    val profileUrl: String
        get() = "$photoUrl$profile_path"
}
