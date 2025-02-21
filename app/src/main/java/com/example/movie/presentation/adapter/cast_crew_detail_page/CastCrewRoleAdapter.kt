package com.example.movie.presentation.adapter.cast_crew_detail_page

import android.view.LayoutInflater
import android.view.ViewGroup
import com.example.movie.R
import com.example.movie.common.base.adapter.BaseAdapter
import com.example.movie.common.utils.ImageLoader
import com.example.movie.databinding.ItemCastCrewBinding
import com.example.movie.domain.model.cast_crew_roles.CastCrewItem

class CastCrewRoleAdapter(
    private val onRootClick: ((CastCrewItem) -> Unit),
): BaseAdapter<CastCrewItem, ItemCastCrewBinding>(emptyList()) {

    override fun createBinding(inflater: LayoutInflater, parent: ViewGroup): ItemCastCrewBinding {
        return ItemCastCrewBinding.inflate(inflater, parent, false)
    }

    override fun bind(binding: ItemCastCrewBinding, item: CastCrewItem, position: Int) {
        ImageLoader.loadImage(
            binding.ivMoviePoster,
            item.posterPath?.toString(),
            R.drawable.placeholder,
            R.drawable.error_image
        )
        binding.tvMovieYear.text = item.year.toString()
        binding.tvVoteAverage.text = item.voteAverage.toString()

        binding.tvMovieTitle.text=item.title
        binding.tvRole.text = item.role
        binding.root.setOnClickListener {
            onRootClick?.invoke(item)

        }



    }
}
