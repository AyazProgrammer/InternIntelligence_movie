package com.example.movie.domain.usecase.actors

import com.example.movie.domain.model.actors_movies.ActorResult
import com.example.movie.domain.repository.ActorRepository

class GetAllActorsUseCase (private val repository: ActorRepository) {
    suspend operator fun invoke(page: Int? = null): ActorResult {
        val currentPage = page ?: 1

        return repository.getActors(currentPage)

    }
}