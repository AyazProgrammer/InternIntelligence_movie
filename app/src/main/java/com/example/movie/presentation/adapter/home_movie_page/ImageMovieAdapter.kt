package com.example.movie.presentation.adapter.home_movie_page

import android.view.LayoutInflater
import android.view.ViewGroup
import com.example.movie.common.base.adapter.BaseAdapter
import com.example.movie.common.utils.ImageLoader
import com.example.movie.databinding.ItemImageBinding
import com.example.movie.domain.model.movie.Movie

class ImageMovieAdapter(): BaseAdapter<Movie, ItemImageBinding>(emptyList()) {

    override fun createBinding(inflater: LayoutInflater, parent: ViewGroup): ItemImageBinding {
        return ItemImageBinding.inflate(inflater, parent, false)
    }

    override fun bind(binding: ItemImageBinding, item: Movie, position: Int) {
        ImageLoader.loadImage2(binding.movieImage, item.posterUrl)
    }


}