package com.example.movie.data.mapper.movie

import com.example.movie.common.base.mapper.BaseMapper
import com.example.movie.data.local.entity.MovieEntity
import com.example.movie.data.remote.model.entity.movie.MovieRemote
import com.example.movie.data.remote.model.entity.movie_detail.CastMemberRemote
import com.example.movie.data.remote.model.response.movie.MovieResponse
import com.example.movie.domain.model.cast_crew.CastMember
import com.example.movie.domain.model.movie.Movie
import com.example.movie.domain.model.movie.MovieResult

object MovieResultMapper:BaseMapper<MovieResponse,MovieResult,MovieEntity> {
    override fun MovieResponse.toDomain(): MovieResult {
        return  MovieResult(
            page = this.page,
            results = this.results.toDomainMovies(),
            total_pages = this.total_pages,
            total_results = this.total_results
        )
    }
    fun List<MovieRemote>.toDomainMovies(): List<Movie> {
        return this.map { MovieRemote ->
            Movie(
                title = MovieRemote.title,
                overview = MovieRemote.overview,
                posterPath = MovieRemote.poster_path,
                releaseDate = MovieRemote.release_date,
                voteAverage = MovieRemote.vote_average,
                voteCount = MovieRemote.vote_count,
                id = MovieRemote.id
            )
        }
    }
    fun Movie.toEntityMovie():MovieEntity{
        return MovieEntity(
            id = this.id,
            title = this.title,
            overview = this.overview,
            posterPath = this.posterPath,
            releaseDate = this.releaseDate,
            voteAverage = this.voteAverage,
            voteCount = this.voteCount
        )
    }
    fun MovieEntity.toDomainMovie():Movie{
        return Movie(
            id = this.id,
            title = this.title,
            overview = this.overview,
            posterPath = this.posterPath,
            releaseDate = this.releaseDate,
            voteAverage = this.voteAverage,
            voteCount = this.voteCount
        )
    }




}