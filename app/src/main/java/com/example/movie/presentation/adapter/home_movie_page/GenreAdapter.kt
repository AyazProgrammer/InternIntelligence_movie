package com.example.movie.presentation.adapter.home_movie_page

import android.view.LayoutInflater
import android.view.ViewGroup
import com.example.movie.R
import com.example.movie.common.base.adapter.BaseAdapter
import com.example.movie.databinding.ItemGenreBinding
import com.example.movie.domain.model.genre.Genre

class GenreAdapter (
    private val onRootClick: ((Genre) -> Unit)? = null,
): BaseAdapter<Genre, ItemGenreBinding>(emptyList()) {

    override fun createBinding(inflater: LayoutInflater, parent: ViewGroup): ItemGenreBinding {
        return ItemGenreBinding.inflate(inflater, parent, false)
    }
    private var selectedPosition: Int = -1

    override fun bind(binding: ItemGenreBinding, item: Genre, position: Int) {
        binding.cardView.text = item.name
        binding.cardView.setOnClickListener {
            onRootClick?.invoke(item)
        }
        if (position == selectedPosition) {
            binding.cardView.setBackgroundColor(
                binding.root.context.getColor(R.color.setBackColor) // Seçilmiş rəng
            )
        } else {
            binding.cardView.setBackgroundColor(
                binding.root.context.getColor(R.color.defaultBackColor) // Default rəng
            )
        }
        binding.cardView.setOnClickListener {
            selectedPosition = position
            notifyDataSetChanged()
            onRootClick?.invoke(item)
        }
    }


}