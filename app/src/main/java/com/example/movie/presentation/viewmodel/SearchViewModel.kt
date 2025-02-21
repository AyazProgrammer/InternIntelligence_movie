package com.example.movie.presentation.viewmodel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.movie.common.base.viewmodel.BaseViewModel
import com.example.movie.domain.model.filter.FilterItem
import com.example.movie.domain.model.genre.Genre
import com.example.movie.domain.model.movie.Movie
import com.example.movie.domain.model.movie.MovieResult
import com.example.movie.domain.usecase.home.GetAllGenresUseCase
import com.example.movie.domain.usecase.search.FilterMovieUseCase
import com.example.movie.domain.usecase.search.SearchMovieUseCase

class SearchViewModel(
    private val searchMovieUseCase: SearchMovieUseCase,
    private val getAllGenresUseCase: GetAllGenresUseCase,
    private val getFilterMovieUseCase: FilterMovieUseCase
) : BaseViewModel(){

    init {
        getAllGenres()
    }

    private val _searchFilter = MutableLiveData<MovieResult>()
    val searchFilter: LiveData<MovieResult> get() = _searchFilter
    private val _genres = MutableLiveData<List<Genre>>()
    val genres: LiveData<List<Genre>> get() = _genres
    private val _key = MutableLiveData<FilterItem?>()
    val key: LiveData<FilterItem?> get() = _key



    fun searchMovies(query:String?=null, id: Int? = null,key:FilterItem?=_key.value) {
        setLoading(true)
        launchSafely(
            block = {
                Log.e("get search id","$key")
                Log.e("get search id","$id")
                if (!query.isNullOrEmpty()) {
                    val result = searchMovieUseCase(query,id)
                    Log.d("search","$result")
                    _key.value = null
                    _searchFilter.postValue(result)
                }else if(key!=null){
                    val result = getFilterMovieUseCase(key,id)
                    Log.d("Filter","$result")
                    _key.postValue(key)
                    _searchFilter.postValue(result)
                }else{
                    val result = searchMovieUseCase(null,id)
                    Log.d("search and filter","$result")
                    _searchFilter.postValue(result)
                }


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
    fun getAllCategoryList(): List<Genre> {
        return genres.value!!
    }

}