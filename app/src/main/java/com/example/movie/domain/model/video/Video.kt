package com.example.movie.domain.model.video

data class Video(
    val id: String,
    val name: String,
    val key: String,
    val site: String,
    val size: Int,
    val type: String,
    val official: Boolean,
    val published_at: String,
){


    val videoUrl: String
        get() = "https://www.youtube.com/watch?v=$key"

    val youTubeUrl: String
        get()  = "https://www.youtube.com/embed/$key?autoplay=1&vq=hd1080"
}



