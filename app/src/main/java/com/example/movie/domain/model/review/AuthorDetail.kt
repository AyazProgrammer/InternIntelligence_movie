package com.example.movie.domain.model.review

import com.example.movie.common.base.model.Media

data class AuthorDetail (
    val name: String?,
    val username: String?,
    val avatar_path: String?,
    val rating: Int?
): Media(){
    val avatarUrl: String
        get() = "$photoUrl$avatar_path"
}