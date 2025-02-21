package com.example.movie.data.repository

import android.util.Log
import com.example.movie.data.local.data_source.MovieLocalDataSource
import com.example.movie.data.mapper.detail.DetailMovieMapper.toDomain
import com.example.movie.data.mapper.cast_crew.MemberMapper.toDomain
import com.example.movie.data.mapper.filter.FilterItemMapper.toRequestModel
import com.example.movie.data.mapper.movie.MovieResultMapper.toDomain
import com.example.movie.data.mapper.movie.MovieResultMapper.toDomainMovie
import com.example.movie.data.mapper.movie.MovieResultMapper.toEntityMovie
import com.example.movie.data.mapper.video.VideoMapper.toDomain
import com.example.movie.data.remote.data_source.movie.MovieRemoteDataSource
import com.example.movie.domain.model.cast_crew.Credits
import com.example.movie.domain.model.detail.DetailMovie
import com.example.movie.domain.model.filter.FilterItem
import com.example.movie.domain.model.movie.Movie
import com.example.movie.domain.model.movie.MovieResult
import com.example.movie.domain.model.video.Video
import com.example.movie.domain.repository.MovieRepository

class MovieRepositoryImpl(
    private val remoteDataSource: MovieRemoteDataSource,
    private val localDataSource: MovieLocalDataSource
) : MovieRepository {
    override suspend fun getMovieWithDetail(movieId: Int): DetailMovie {
        return remoteDataSource.getMoviesDetail(movieId).toDomain()
    }

    override suspend fun getMovieForGenre(genreId: Int, page: Int): MovieResult {
        return remoteDataSource.getMovieForGenre(genreId,page).toDomain()
    }

    override suspend fun getList(page: Int): MovieResult {
        TODO("Get All with Detail")
    }

    override suspend fun getList(): List<Movie> {
        TODO("Get All with Detail")
    }

    override suspend fun getPopularMovies(page: Int): MovieResult {
        return remoteDataSource.getPopularMovies(page).toDomain()
    }

    override suspend fun getNowPlayingMovies(page: Int): MovieResult {
        return remoteDataSource.getNowPlayingMovies(page).toDomain()
    }

    override suspend fun getSimilarMovies(movieId: Int, page: Int):MovieResult {
        return remoteDataSource.getSimilarMovies(movieId,page).toDomain()
    }



    override suspend fun getVideos(movieId: Int): List<Video> {
        return remoteDataSource.getMovieVideos(movieId).map { it.toDomain() }
    }

    override suspend fun getAllMovies(page: Int): MovieResult {
        return remoteDataSource.getAllMovies(page).toDomain()
    }

    override suspend fun searchMovies(query: String, page: Int): MovieResult {
        return remoteDataSource.searchVideo(query,page).toDomain()
    }

    override suspend fun getPersons(movieId: Int): Credits {
        return remoteDataSource.getMembers(movieId).toDomain()
    }

    override suspend fun saveMovie(movie: Movie) {
        localDataSource.saveEntity(movie.toEntityMovie())
    }

    override suspend fun getAllMovieEntity(): List<Movie> {
        return localDataSource.getEntities().map { it.toDomainMovie()
        }
    }

    override suspend fun checkMovieInDataBase(id: Int): Boolean {
        return localDataSource.checkMovieEntity(id)
    }

    override suspend fun deleteMovie(movie: Movie) {
       localDataSource.deleteEntity(movie.toEntityMovie())
    }

    override suspend fun getUpComingMovies(page: Int): MovieResult {
        return remoteDataSource.getUpComingMovies(page).toDomain()
    }

    override suspend fun getTopRatedMovies(page: Int): MovieResult {
        return remoteDataSource.getTopRatedMovies(page).toDomain()
    }
}