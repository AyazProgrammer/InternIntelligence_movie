package com.example.movie.domain.usecase.detail

import com.example.movie.domain.model.video.Video
import com.example.movie.domain.repository.MovieRepository

class GetVideosMovieUseCase(private val repository: MovieRepository) {
    suspend operator fun invoke(movieId :Int, page: Int? = null):List<Video> {

        return repository.getVideos(movieId)

    }
}