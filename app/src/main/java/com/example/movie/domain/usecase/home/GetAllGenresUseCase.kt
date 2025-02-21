package com.example.movie.domain.usecase.home

import com.example.movie.domain.model.genre.Genre
import com.example.movie.domain.repository.GenreRepository

class GetAllGenresUseCase(private val repository: GenreRepository) {
    suspend operator fun invoke(): List<Genre> {
        return repository.getList()

    }
}