package com.example.movie.data.remote.model.request.register

data class User(val email: String, val password: String, val name: String? = null,val avatar:String?=null)