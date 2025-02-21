package com.example.movie.data.remote.data_source.movie

import com.example.movie.data.base.BaseRemoteDataSource
import com.example.movie.data.remote.model.filter.FilterRequest
import com.example.movie.data.remote.model.entity.movie.MovieRemote
import com.example.movie.data.remote.model.entity.movie_detail.VideoResult
import com.example.movie.data.remote.model.response.movie.MovieResponse
import com.example.movie.data.remote.model.response.movie_detail.DetailMovieResponse
import com.example.movie.data.remote.model.response.movie_detail.MovieCreditsResponse
import com.example.movie.domain.model.filter.FilterItem

interface MovieRemoteDataSource: BaseRemoteDataSource<MovieResponse> {

    suspend fun getPopularMovies(page: Int): MovieResponse

    suspend fun getTopRatedMovies(page: Int): MovieResponse
    suspend fun getUpComingMovies(page: Int):MovieResponse

    suspend fun getSimilarMovies(movieId:Int,page: Int):MovieResponse

    suspend fun getMovieVideos(movieId:Int):List<VideoResult>

    suspend fun getMovieForGenre(genreId: Int,page: Int): MovieResponse

    suspend fun getNowPlayingMovies(page: Int): MovieResponse

    suspend fun getMoviesDetail(movieId:Int): DetailMovieResponse
    suspend fun getAllMovies(page: Int):MovieResponse
    suspend fun getMembers(movieId:Int): MovieCreditsResponse
    suspend fun getEntities():MovieResponse
    suspend fun searchVideo(query:String,page:Int):MovieResponse



}