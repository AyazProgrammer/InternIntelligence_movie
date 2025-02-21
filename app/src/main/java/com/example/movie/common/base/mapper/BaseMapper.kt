package com.example.movie.common.base.mapper

interface BaseMapper< in R, out D, E> {
    fun R.toDomain(): D? = null
    fun R.toEntity(): E? = null
    fun E.toEntityToDomain(): D? = null
    fun @UnsafeVariance D.toRequestModel(): @UnsafeVariance R?= null
}
