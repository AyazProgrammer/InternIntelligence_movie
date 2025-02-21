package com.example.movie.domain.model.actors_movies

import com.example.movie.common.base.model.Media
import com.example.movie.data.remote.model.entity.movie.MovieRemote
import com.example.movie.domain.model.movie.Movie
import com.google.gson.annotations.SerializedName

data class Actor(
    val id: Int,
    val name: String,
    val originalName: String,
    val knownForDepartment:String,
    val popularity: Double,
    val profilePath: String?,
    val knownFor: List<Movie>
):Media(){
    val profileUrl: String
        get() = "$photoUrl$profilePath"
}
