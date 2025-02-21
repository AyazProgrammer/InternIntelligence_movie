package com.example.movie.common.base.viewmodel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch

abstract class BaseViewModel : ViewModel() {


    private val job = Job()
    protected val viewModelScope = CoroutineScope(Dispatchers.Main + job)


    private val _loading = MutableLiveData<Boolean>()
    val loading: LiveData<Boolean> get() = _loading

    private val _errorMessage = MutableLiveData<String>()
    open val errorMessage: LiveData<String> get() = _errorMessage


    protected fun setLoading(isLoading: Boolean) {
        _loading.value = isLoading
    }


    protected fun handleError(exception: Exception) {
        Log.e(
            "Error data fetch",
            "${exception.stackTrace}, ${exception.localizedMessage} ${exception.cause}"
        )
        _errorMessage.value = exception.localizedMessage
    }


    protected fun launchSafely(
        block: suspend CoroutineScope.() -> Unit,
        onError: (Exception) -> Unit = { handleError(it) }
    ) {
        viewModelScope.launch {
            try {
                block()
            } catch (e: Exception) {
                onError(e)
            }
        }
    }


    override fun onCleared() {
        super.onCleared()
        job.cancel()
    }
}