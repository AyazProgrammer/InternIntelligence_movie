package com.example.movie.common.factory

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.movie.presentation.viewmodel.HomeViewModel

class CommonViewModelFactory<T : ViewModel>(
    private val creator: () -> T  // Generic creator function
) : ViewModelProvider.Factory {

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return if (modelClass.isAssignableFrom(creator().javaClass)) {
            creator() as T  // Cast to the appropriate ViewModel
        } else {
            throw IllegalArgumentException("Unknown ViewModel class: $modelClass")
        }
    }
}

