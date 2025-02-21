package com.example.movie.data.mapper.filter

import com.example.movie.common.base.mapper.BaseMapper
import com.example.movie.data.remote.model.filter.FilterRequest
import com.example.movie.domain.model.filter.FilterItem

object FilterItemMapper:BaseMapper<FilterRequest,FilterItem,Nothing> {
    override fun FilterItem.toRequestModel(): FilterRequest {
        return FilterRequest(
            with_genres = this.genres,
            vote_average_gte = this.imdbRating,
            year = this.year
        )
    }
}