package com.example.movie.common.factory

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider

class CoreViewModelFactory<T : ViewModel>(
    private val context: Context,
    private val creator: (Context) -> T
) : ViewModelProvider.Factory {

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return if (modelClass.isAssignableFrom(creator(context)::class.java)) {
            creator(context) as T
        } else {
            throw IllegalArgumentException("Unknown ViewModel class: $modelClass")
        }
    }
}