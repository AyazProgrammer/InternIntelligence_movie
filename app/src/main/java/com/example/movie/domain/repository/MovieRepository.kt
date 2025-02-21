package com.example.movie.domain.repository

import com.example.movie.common.base.repository.Repository
import com.example.movie.domain.model.cast_crew.Credits
import com.example.movie.domain.model.detail.DetailMovie
import com.example.movie.domain.model.filter.FilterItem
import com.example.movie.domain.model.movie.Movie
import com.example.movie.domain.model.movie.MovieResult
import com.example.movie.domain.model.video.Video


interface MovieRepository: Repository<Movie> {

    suspend fun getMovieWithDetail(movieId:Int): DetailMovie
    suspend fun getMovieForGenre(genreId: Int, page: Int): MovieResult
    suspend fun getList(page:Int): MovieResult
    suspend fun getPopularMovies(page: Int):MovieResult
    suspend fun getTopRatedMovies(page: Int):MovieResult
    suspend fun getUpComingMovies(page: Int): MovieResult
    suspend fun getNowPlayingMovies(page: Int): MovieResult
    suspend fun getSimilarMovies(movieId:Int,page: Int): MovieResult
    suspend fun getAllMovies(page:Int):MovieResult
    suspend fun searchMovies(query:String,page: Int):MovieResult
    suspend fun getVideos(movieId:Int): List<Video>
    suspend fun getPersons(movieId: Int): Credits

    suspend fun saveMovie(movie: Movie)
    suspend fun deleteMovie(movie: Movie)
    suspend fun getAllMovieEntity():List<Movie>
    suspend fun checkMovieInDataBase(id:Int):Boolean


}