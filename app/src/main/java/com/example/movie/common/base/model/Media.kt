package com.example.movie.common.base.model

abstract class Media {

    open val photoUrl: String
        get() = "https://image.tmdb.org/t/p/w500"
}