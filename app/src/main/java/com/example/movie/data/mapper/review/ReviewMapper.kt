package com.example.movie.data.mapper.review

import com.example.movie.common.base.mapper.BaseMapper
import com.example.movie.data.remote.model.entity.review.AuthorDetailsRemote
import com.example.movie.data.remote.model.entity.review.UserReviewRemote
import com.example.movie.data.remote.model.response.movie_detail.ReviewsResponse
import com.example.movie.domain.model.review.AuthorDetail
import com.example.movie.domain.model.review.Review
import com.example.movie.domain.model.review.UserReview

object ReviewMapper:BaseMapper<ReviewsResponse,Review,Nothing> {
    override fun ReviewsResponse.toDomain(): Review {
        return Review(
            id = this.id,
            page = this.page,
            results = this.results.toDomainUserReview(),
            total_pages = this.total_pages,
            total_results = this.total_results
        )
    }
    fun List<UserReviewRemote>.toDomainUserReview(): List<UserReview> {
        return this.map { UserReviewRemote ->
            UserReview(
                author = UserReviewRemote.author,
                author_details = UserReviewRemote.author_details.toDomainAuthorDetail(),
                content = UserReviewRemote.content,
                created_at = UserReviewRemote.created_at,
                id = UserReviewRemote.id,
                updated_at = UserReviewRemote.updated_at,
                url = UserReviewRemote.url
            )
        }
    }
   fun AuthorDetailsRemote.toDomainAuthorDetail(): AuthorDetail {
        return AuthorDetail(
            name = this.name,
            username = this.username,
            avatar_path = this.avatar_path,
            rating = this.rating
        )
    }
}