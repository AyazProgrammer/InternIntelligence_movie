package com.example.movie.data.mapper.video

import com.example.movie.common.base.mapper.BaseMapper
import com.example.movie.data.remote.model.entity.movie_detail.VideoResult
import com.example.movie.domain.model.video.Video

object VideoMapper:BaseMapper<VideoResult, Video,Nothing> {
    override fun VideoResult.toDomain(): Video {
        return  Video(
            id = this.id,
            name = this.name,
            key = this.key,
            site = this.site,
            size = this.size,
            type = this.type,
            official = this.official,
            published_at = this.published_at,

        )
    }
}