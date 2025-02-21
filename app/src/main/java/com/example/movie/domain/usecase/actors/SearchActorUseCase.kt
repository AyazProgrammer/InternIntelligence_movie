package com.example.movie.domain.usecase.actors

import com.example.movie.domain.model.actors_movies.ActorResult
import com.example.movie.domain.repository.ActorRepository

class SearchActorUseCase(private val repository: ActorRepository) {
    suspend operator fun invoke(query:String?=null,page: Int? = null): ActorResult {
        val currentPage = page ?: 1
        val result:ActorResult
        if (query.isNullOrBlank()){
             result= repository.getActors(currentPage)
        }else{
            result= repository.searchActor(query,currentPage)
        }
        return ActorResult(
            page = result.page,
            results = result.results.filter { it.popularity>1 && !(it.profilePath.isNullOrEmpty()) },
            totalPages = result.totalPages,
            totalResults = result.totalResults
        )

    }
}