package com.example.movie.presentation.viewmodel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.movie.common.base.viewmodel.BaseViewModel
import com.example.movie.domain.model.movie.Movie
import com.example.movie.domain.usecase.favorites.DeleteFavMovieUseCase
import com.example.movie.domain.usecase.favorites.GetAllFavorMoviesUseCase

class FavoritesViewModel(
    private  val getAllFavorMoviesUseCase: GetAllFavorMoviesUseCase,
    private  val deleteFavMovieUseCase: DeleteFavMovieUseCase
):BaseViewModel() {

    private val _favor = MutableLiveData<List<Movie>>()
    val favor: LiveData<List<Movie>> get() = _favor

    init {
        getFavMovies()
    }

    fun getFavMovies() {
        setLoading(true)
        launchSafely(
            block = {

                val result = getAllFavorMoviesUseCase()
                Log.d("favor data", "$result")
                _favor.postValue(result)


            },
            onError = {
                handleError(it)
            }
        ).also {
            setLoading(false)
        }
    }

    fun deleteFavMovie(movie: Movie) {
        setLoading(true)
        launchSafely(
            block = {

                deleteFavMovieUseCase(movie)
                getFavMovies()


            },
            onError = {
                handleError(it)
            }
        ).also {
            setLoading(false)
        }
    }
}