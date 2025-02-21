package com.example.movie.data.remote.data_source.movie

import android.util.Log
import com.example.movie.data.remote.model.filter.FilterRequest
import com.example.movie.data.remote.api.MovieApiService
import com.example.movie.data.remote.model.entity.movie.MovieRemote
import com.example.movie.data.remote.model.entity.movie_detail.VideoResult
import com.example.movie.data.remote.model.response.movie.MovieResponse
import com.example.movie.data.remote.model.response.movie_detail.DetailMovieResponse
import com.example.movie.data.remote.model.response.movie_detail.MovieCreditsResponse

class MovieRemoteDataSourceImpl(private val apiService: MovieApiService) : MovieRemoteDataSource {
    override suspend fun getMoviesDetail(movieId: Int): DetailMovieResponse {
        val endPoint ="movie/$movieId"
        return apiService.getMoviesWithDetail(endPoint)
    }



    override suspend fun getMembers(movieId: Int): MovieCreditsResponse {
        val endPoint = "movie/{movieId}/credits"

        val finalEndPoint = endPoint.replace("{movieId}", movieId.toString())
        return apiService.getWorker(finalEndPoint)
    }

    override suspend fun getSimilarMovies(movieId: Int, page: Int):MovieResponse {
        val endPoint = "movie/{movieId}/similar"

        val finalEndPoint = endPoint.replace("{movieId}", movieId.toString())
        return apiService.getSimilarMovies(finalEndPoint, page)
    }

    override suspend fun getMovieVideos(movieId: Int): List<VideoResult> {
        val endPoint = "movie/{movieId}/videos"

        val finalEndPoint = endPoint.replace("{movieId}", movieId.toString())
        return apiService.getMovieVideos(finalEndPoint).results
    }

    override suspend fun getPopularMovies(page: Int): MovieResponse {
        return apiService.getMovies("movie/popular", page)
    }

    override suspend fun getTopRatedMovies(page: Int): MovieResponse {
        return apiService.getMovies("movie/top_rated", page)
    }

    override suspend fun getUpComingMovies(page: Int): MovieResponse {
        return apiService.getMovies("movie/upcoming", page)
    }

    override suspend fun getNowPlayingMovies(page: Int): MovieResponse {
        return apiService.getMovies("movie/now_playing", page)
    }

    override suspend fun getMovieForGenre(genreId: Int, page: Int): MovieResponse {
        return apiService.getMoviesforGenre("discover/movie", genreId,page)
    }

    override suspend fun getAllMovies (page: Int): MovieResponse {
        val endPoint = "discover/movie"

        return apiService.getAllVideo(endPoint,page)
    }

    override suspend fun searchVideo(query:String,page:Int): MovieResponse {
        val endPoint = "search/movie"
        return apiService.searchVideo(endPoint,query,page)
    }



    override suspend fun getEntities(): MovieResponse {
        return apiService.getMovies("movie/now_playing", 1)
    }
}
