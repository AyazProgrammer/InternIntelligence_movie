package com.example.movie.presentation.adapter.home_movie_page

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.movie.databinding.ItemSelectedGenreBinding
import com.example.movie.domain.model.genre.Genre

class SelectedGenresAdapter(
    private val genres: List<Genre>,
    private val onRemoveGenre: (Genre) -> Unit
) : RecyclerView.Adapter<SelectedGenresAdapter.SelectedGenreViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SelectedGenreViewHolder {
        val binding = ItemSelectedGenreBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return SelectedGenreViewHolder(binding)
    }

    override fun onBindViewHolder(holder: SelectedGenreViewHolder, position: Int) {
        val genre = genres[position]
        holder.bind(genre)
    }

    override fun getItemCount(): Int = genres.size

    inner class SelectedGenreViewHolder(private val binding: ItemSelectedGenreBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(genre: Genre) {
            binding.genreText.text = genre.name
            binding.removeButton.setOnClickListener {
                onRemoveGenre(genre)
            }
        }
    }
}
