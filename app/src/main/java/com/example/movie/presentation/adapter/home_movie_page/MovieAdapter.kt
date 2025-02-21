package com.example.movie.presentation.adapter.home_movie_page


import android.view.LayoutInflater
import android.view.ViewGroup
import com.example.movie.common.base.adapter.BaseAdapter
import com.example.movie.common.extensions.reverseReleaseDate
import com.example.movie.common.extensions.toRoundedString
import com.example.movie.common.utils.ImageLoader
import com.example.movie.databinding.ItemMovieBinding
import com.example.movie.domain.model.movie.Movie

class MovieAdapter(
    private val onPosterClick: ((Movie) -> Unit)? = null,
    private val onRootClick: ((Movie) -> Unit)? = null,
    private val onOverviewClick: ((Movie) -> Unit)? = null
) : BaseAdapter<Movie, ItemMovieBinding>(emptyList()) {

    override fun createBinding(inflater: LayoutInflater, parent: ViewGroup): ItemMovieBinding {
        return ItemMovieBinding.inflate(inflater, parent, false)
    }

    override fun bind(binding: ItemMovieBinding, item: Movie, position: Int) {
        binding.movieTitle.text = item.title
        ImageLoader.loadImage2(binding.moviePoster, item.posterUrl)
        binding.textOverview.text = item.overview
        binding.textReleaseDate.text = item.releaseDate.reverseReleaseDate()
        binding.textVoteAverage.text = item.voteAverage.toDouble().toRoundedString()
        binding.textVoteCount.text = "( ${item.voteCount} votes )"

        binding.moviePoster.setOnClickListener {
            onPosterClick?.invoke(item)
        }

        binding.root.setOnClickListener {
            onRootClick?.invoke(item)
        }

        binding.textOverview.setOnClickListener {
            onOverviewClick?.invoke(item)
        }
    }
}


