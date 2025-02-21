package com.example.movie.presentation.viewmodel


import com.example.movie.common.base.viewmodel.BaseViewModel

import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class SplashViewmodel() : BaseViewModel() {
    private val _splashTime = MutableStateFlow(false)
    var splashTime = _splashTime.asStateFlow()
      fun splash ()  {
         viewModelScope.launch {
             delay(6000)
             _splashTime.value = true

         }
     }


}