package com.example.movie.domain.usecase.detail

import com.example.movie.domain.model.cast_crew.Credits
import com.example.movie.domain.repository.MovieRepository

class GetPersonsMovieUseCase (private val repository: MovieRepository) {
    suspend operator fun invoke(movieId: Int): Credits {
        val moviePersons = repository.getPersons(movieId)

        return Credits(
            id = moviePersons.id,
            cast = moviePersons.cast.filter { !it.profile_path.isNullOrEmpty() },
            crew =  moviePersons.crew.filter { !it.profile_path.isNullOrEmpty() },
        )

    }
}