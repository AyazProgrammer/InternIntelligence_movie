package com.example.movie.data.remote.model.response.movie_detail

import com.example.movie.data.remote.model.entity.movie_detail.VideoResult

data class VideoResponse(
    val id: Int,
    val results: List<VideoResult>
)
