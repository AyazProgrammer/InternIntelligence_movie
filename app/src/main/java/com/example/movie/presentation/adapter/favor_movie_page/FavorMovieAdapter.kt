package com.example.movie.presentation.adapter.favor_movie_page

import android.view.Gravity
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.PopupMenu
import android.widget.Toast
import com.example.movie.R
import com.example.movie.common.base.adapter.BaseAdapter
import com.example.movie.common.extensions.reverseReleaseDate
import com.example.movie.common.extensions.toRoundedString
import com.example.movie.common.utils.ImageLoader
import com.example.movie.databinding.ItemCardBinding
import com.example.movie.domain.model.movie.Movie

class FavorMovieAdapter (
    private val onPosterClick: ((Movie) -> Unit)? = null,
    private val onDeleteClick: ((Movie) -> Unit)? = null,
    private val onOverviewClick: ((Movie) -> Unit)? = null
) : BaseAdapter<Movie, ItemCardBinding>(emptyList()) {

    override fun createBinding(inflater: LayoutInflater, parent: ViewGroup): ItemCardBinding {
        return ItemCardBinding.inflate(inflater, parent, false)
    }

    override fun bind(binding: ItemCardBinding, item: Movie, position: Int) {
        binding.tvMovieTitle.text = item.title
        ImageLoader.loadImage2(binding.ivMoviePoster, item.posterUrl)
        binding.tvMovieRating.text = item.voteAverage.toDouble().toRoundedString()
         binding.tvMovieYear.text = item.releaseDate.reverseReleaseDate()

        binding.root.setOnClickListener {
            onPosterClick?.invoke(item)


        }

        binding.btnMenu.setOnClickListener { view ->
            val popupMenu = PopupMenu(view.context, view,Gravity.TOP)
            popupMenu.menuInflater.inflate(R.menu.movie_item_menu, popupMenu.menu)

            popupMenu.setOnMenuItemClickListener { menuItem ->
                when (menuItem.itemId) {
                    R.id.action_delete -> {
                        onDeleteClick?.invoke(item)
                        notifyDataSetChanged()
                        Toast.makeText(view.context, "Deleted", Toast.LENGTH_SHORT).show()
                        true
                    }
                    else -> false
                }
            }

            popupMenu.show()
        }






    }


}