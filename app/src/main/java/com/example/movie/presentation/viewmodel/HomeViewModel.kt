package com.example.movie.presentation.viewmodel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.movie.common.base.viewmodel.BaseViewModel
import com.example.movie.domain.model.genre.Genre
import com.example.movie.domain.model.movie.Movie
import com.example.movie.domain.model.movie.MovieResult
import com.example.movie.domain.usecase.home.GetAllGenresUseCase
import com.example.movie.domain.usecase.home.GetMovieWithGenreUseCase
import com.example.movie.domain.usecase.home.GetNowPlayingUseCase
import com.example.movie.domain.usecase.home.GetTopRatedMoviesUseCase
import com.example.movie.domain.usecase.home.GetPopularMoviesUseCase
import com.example.movie.domain.usecase.home.GetUpComingMoviesUseCase

class HomeViewModel(
    private val getPopularMoviesUseCase: GetPopularMoviesUseCase,
    private val getTopRatedMoviesUseCase: GetTopRatedMoviesUseCase,
    private val getUpComingMoviesUseCase: GetUpComingMoviesUseCase,
    private val getNowPlayingUseCase: GetNowPlayingUseCase,
    private val getAllGenresUseCase: GetAllGenresUseCase,
    private val getMovieWithGenreUseCase: GetMovieWithGenreUseCase


    ) : BaseViewModel() {

    private val _popular = MutableLiveData<MovieResult>()
    val popular: LiveData<MovieResult> get() = _popular


    private val _topRated = MutableLiveData<MovieResult>()
    val topRated: LiveData<MovieResult> get() = _topRated

    private val _genres = MutableLiveData<List<Genre>>()
    val genres: LiveData<List<Genre>> get() = _genres

    private val _upComing = MutableLiveData<MovieResult>()
    val upComing: LiveData<MovieResult> get() = _upComing

    private val _nowPlaying = MutableLiveData<MovieResult>()
    val nowPlaying: LiveData<MovieResult> get() = _nowPlaying

    private val _data = MutableLiveData<MovieResult>()
    val data: LiveData<MovieResult> get() = _data


    init {

        getPopularMovies(1)
        getAllGenres()
        getTopRatedMovies()
        getUpComingMovies()
        getNowPlayingMovies()


    }


    fun getPopularMovies(id: Int? = null) {
        setLoading(true)
        launchSafely(
            block = {

                val result = getPopularMoviesUseCase(id)
                Log.d("All data","$result")
                _popular.postValue(result)

            },
            onError = {
                handleError(it)
            }
        ).also {
            setLoading(false)
        }
    }

    fun getTopRatedMovies(id: Int? = null) {
        setLoading(true)
        launchSafely(
            block = {

                val result = getTopRatedMoviesUseCase(id)
                Log.d("All data","$result")
                _topRated.postValue(result)

            },
            onError = {
                handleError(it)
            }
        ).also {
            setLoading(false)
        }
    }

    fun getUpComingMovies(id: Int? = null) {
        setLoading(true)
        launchSafely(
            block = {

                val result = getUpComingMoviesUseCase(id)
                Log.d("All data","$result")
                _upComing.postValue(result)

            },
            onError = {
                handleError(it)
            }
        ).also {
            setLoading(false)
        }
    }

     fun getNowPlayingMovies(id: Int? = null) {
        setLoading(true)
        launchSafely(
            block = {

                val result = getNowPlayingUseCase(id)
                Log.d("All upcoming data","$result")
                _nowPlaying.postValue(result)

            },
            onError = {
                handleError(it)
            }
        ).also {
            setLoading(false)
        }
    }

    fun getAllGenres() {
        setLoading(true)
        launchSafely(
            block = {

                val result = getAllGenresUseCase()
                Log.d("All genres","$result")
                _genres.postValue(result)

            },
            onError = {
                handleError(it)
            }
        ).also {
            setLoading(false)
        }
    }

    fun getAllMovieWithGenre(genre: Int? = null, page: Int? = null) {
        setLoading(true)
        launchSafely(
            block = {

                val result = getMovieWithGenreUseCase(genre,page)
                Log.d("All genres","$result")
                _data.postValue(result)

            },
            onError = {
                handleError(it)
            }
        ).also {
            setLoading(false)
        }
    }
}