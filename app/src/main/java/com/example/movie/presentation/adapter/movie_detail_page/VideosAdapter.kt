package com.example.movie.presentation.adapter.movie_detail_page

import android.view.LayoutInflater
import android.view.ViewGroup
import com.example.movie.common.base.adapter.BaseAdapter
import com.example.movie.databinding.ItemVideosBinding
import com.example.movie.domain.model.video.Video


class VideosAdapter(
    private val onRootClick: ((Video) -> Unit)? = null
) : BaseAdapter<Video, ItemVideosBinding>(emptyList()) {

    override fun createBinding(inflater: LayoutInflater, parent: ViewGroup): ItemVideosBinding {
        return ItemVideosBinding.inflate(inflater, parent, false)
    }

    override fun bind(binding: ItemVideosBinding, item: Video, position: Int) {
        binding.videoTitle.text = item.name
        binding.videoLink.text =item.videoUrl
        val videoKey = item.key
        val youtubeUrl = "https://www.youtube.com/watch?v=$videoKey"



        binding.videoLink.setOnClickListener {
            onRootClick?.invoke(item)
        }
    }
}
