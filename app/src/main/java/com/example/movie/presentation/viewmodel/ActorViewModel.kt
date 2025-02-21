package com.example.movie.presentation.viewmodel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.movie.common.base.viewmodel.BaseViewModel
import com.example.movie.domain.model.actor_detail.ActorDetailResult
import com.example.movie.domain.model.actors_movies.ActorResult
import com.example.movie.domain.usecase.actors.DetailActorUseCase
import com.example.movie.domain.usecase.actors.GetAllActorsUseCase
import com.example.movie.domain.usecase.actors.SearchActorUseCase

class ActorViewModel(
    private  val getAllActorsUseCase: GetAllActorsUseCase,
    private  val searchActorUseCase: SearchActorUseCase,
    private val detailActorUseCase:DetailActorUseCase
):BaseViewModel() {

    private val _actors = MutableLiveData<ActorResult>()
    val actors: LiveData<ActorResult> get() = _actors

    private val _actorDetails = MutableLiveData<ActorDetailResult>()
    val actorDetails: LiveData<ActorDetailResult> get() = _actorDetails

   // private val _actorMovieCredits = MutableLiveData<MovieCredits>()
   // val actorMovieCredits: LiveData<MovieCredits> get() = _actorMovieCredits

    init {
        getPopularActors()
    }


    fun getPopularActors(id: Int? = null) {
        setLoading(true)
        launchSafely(
            block = {

                val result = getAllActorsUseCase(id)
                Log.d("All data","$result")
                _actors.postValue(result)

            },
            onError = {
                handleError(it)
            }
        ).also {
            setLoading(false)
        }
    }

    fun searchActor(query:String, id: Int? = null) {
        setLoading(true)
        launchSafely(
            block = {
                Log.e("get search id","$id")
                val result = searchActorUseCase(query,id)
              _actors.postValue(result)


            },
            onError = {
                handleError(it)
            }
        ).also {
            setLoading(false)
        }
    }
    fun actorDetails( id: Int ) {
        setLoading(true)
        launchSafely(
            block = {
                Log.e("get search id","$id")
                val result = detailActorUseCase(id)
                _actorDetails.postValue(result)


            },
            onError = {
                handleError(it)
            }
        ).also {
            setLoading(false)
        }
    }
}