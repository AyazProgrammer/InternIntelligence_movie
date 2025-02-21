package com.example.movie.domain.usecase.detail

import com.example.movie.domain.model.detail.DetailMovie
import com.example.movie.domain.model.movie.Movie
import com.example.movie.domain.repository.MovieRepository

class ChangeFavMovieUseCase(private val repository: MovieRepository) {
    suspend operator fun invoke(movieDetail: DetailMovie, check: Boolean):Boolean {
        val movie = Movie(
            id = movieDetail.id,
            title = movieDetail.title,
            overview = movieDetail.overview,
            posterPath = movieDetail.poster_path,
            releaseDate = movieDetail.release_date,
            voteAverage = movieDetail.vote_average.toString(),
            voteCount = movieDetail.vote_count.toString()
        )
        if (check) {
            repository.deleteMovie(movie)
        } else {
            repository.saveMovie(movie)
        }

        return repository.checkMovieInDataBase(movie.id)


    }
}