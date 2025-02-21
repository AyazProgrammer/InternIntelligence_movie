package com.example.movie.domain.usecase.detail

import com.example.movie.domain.model.cast_crew.Credits
import com.example.movie.domain.model.review.Review
import com.example.movie.domain.repository.MovieRepository
import com.example.movie.domain.repository.ReviewRepository

class GetReviewMovieUseCase (private val repository: ReviewRepository) {
    suspend operator fun invoke(movieId: Int): Review {
        return  repository.getReview(movieId)

    }
}