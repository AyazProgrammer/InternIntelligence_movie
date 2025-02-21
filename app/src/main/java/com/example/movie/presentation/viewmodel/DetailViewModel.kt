package com.example.movie.presentation.viewmodel

import android.content.Context
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.movie.common.base.viewmodel.BaseViewModel
import com.example.movie.domain.model.cast_crew.Credits
import com.example.movie.domain.model.detail.DetailMovie
import com.example.movie.domain.model.movie.Movie
import com.example.movie.domain.model.movie.MovieResult
import com.example.movie.domain.model.review.Review
import com.example.movie.domain.model.video.Video
import com.example.movie.domain.usecase.detail.ChangeFavMovieUseCase
import com.example.movie.domain.usecase.detail.CheckMovieUseCase
import com.example.movie.domain.usecase.detail.GetPersonsMovieUseCase
import com.example.movie.domain.usecase.detail.GetDetailMovieUseCase
import com.example.movie.domain.usecase.detail.GetReviewMovieUseCase
import com.example.movie.domain.usecase.detail.GetSimilarMoviesUseCase
import com.example.movie.domain.usecase.detail.GetVideosMovieUseCase
import com.example.movie.domain.usecase.favorites.DeleteFavMovieUseCase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import kotlinx.coroutines.supervisorScope
import kotlinx.coroutines.withContext

class DetailViewModel(
    private val getMovieDetailUseCase: GetDetailMovieUseCase,
    private val getSimilarMoviesUseCase: GetSimilarMoviesUseCase,
    private val getPersonsMovieUseCase: GetPersonsMovieUseCase,
    private val getVideosMovieUseCase: GetVideosMovieUseCase,
    private val getReviewMovieUseCase: GetReviewMovieUseCase,
    private val checkMovieUseCase: CheckMovieUseCase,
    private val changeFavMovieUseCase: ChangeFavMovieUseCase,
    val context: Context

) : BaseViewModel() {

    private val _movieDetail = MutableLiveData<DetailMovie>()
    val movieDetail: LiveData<DetailMovie> get() = _movieDetail


    private val _movieId = MutableLiveData<Int>()
    val movieId: LiveData<Int> get() = _movieId

    private val _similar = MutableLiveData<MovieResult>()
    val similar: LiveData<MovieResult> get() = _similar

    private val _videos = MutableLiveData<List<Video>>()
    val videos: LiveData<List<Video>> get() = _videos

    private val _review = MutableLiveData<Review>()
    val review: LiveData<Review> get() = _review

    private val _persons = MutableLiveData<Credits>()
    val persons: LiveData<Credits> get() = _persons
    private val _isFavorite = MutableLiveData<Boolean>()
    val isFavorite: LiveData<Boolean> get() = _isFavorite


    private suspend fun checkMovie(id: Int):Boolean{
        val result = checkMovieUseCase(id)
        _isFavorite.postValue(result)
        return result
    }


    fun getDetailMovies(id: Int) {
        setLoading(true)
        launchSafely(
            block = {
                supervisorScope {

                    val isCheckDeferred = async {
                        val isCheck = checkMovie(id)

                        isCheck
                    }


                    val movieDetailDeferred = async {
                        val result = getMovieDetailUseCase(id)
                        _movieDetail.postValue(result)
                        result
                    }


                     isCheckDeferred.await()
                     movieDetailDeferred.await()

                    _movieId.postValue(id)
                }
            },
            onError = {
                handleError(it)
            }
        ).also {
            setLoading(false)
        }
    }




    fun getVideos(id: Int) {
        setLoading(true)
        launchSafely(
            block = {
                val result = getVideosMovieUseCase(id)
                Log.d("movie videos", "$result")

                withContext(Dispatchers.Main) {
                    _videos.value = result
                    _movieId.value = id
                }
            },
            onError = {
                handleError(it)
            }
        ).also {
            setLoading(false)
        }
    }


    fun getPersons(id: Int) {
        setLoading(true)
        launchSafely(
            block = {

                val result = getPersonsMovieUseCase(id)
                Log.e("credits alllll", "$result")
                _persons.postValue(result)
                _movieId.postValue(id)

            },
            onError = {
                handleError(it)
            }
        ).also {
            setLoading(false)
        }
    }

    fun getSimilarMovies(id: Int) {
        Log.e("similar film id", "$id")
        setLoading(true)
        launchSafely(
            block = {

                val result = getSimilarMoviesUseCase(id)
                Log.d("All data", "$result")
                _similar.postValue(result)

            },
            onError = {
                handleError(it)
            }
        ).also {
            setLoading(false)
        }
    }
    fun getReviewsForMovie(id: Int) {
        Log.e("similar film id", "$id")
        setLoading(true)
        launchSafely(
            block = {

                val result = getReviewMovieUseCase(id)
                Log.d("All data", "$result")
                _review.postValue(result)

            },
            onError = {
                handleError(it)
            }
        ).also {
            setLoading(false)
        }
    }



    fun changeMovie() {
        setLoading(true)
        launchSafely(
            block = {

                _movieDetail.value?.let {
                    _isFavorite.value?.let { bool->
                        changeFavMovieUseCase(it,bool)
                        checkMovie(it.id)

                    }

                }


            },
            onError = {
                handleError(it)
            }
        ).also {
            setLoading(false)
        }
    }





    fun updateMovieId(movieID: Int) {
        _movieId.postValue(movieID)
    }


}