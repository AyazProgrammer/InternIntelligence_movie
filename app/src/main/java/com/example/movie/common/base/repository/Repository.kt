package com.example.movie.common.base.repository

interface Repository<T> {
    suspend fun getList(): List<T>

}