package com.example.movie.presentation.adapter.movie_detail_page.cast_crew

import android.view.LayoutInflater
import android.view.ViewGroup
import com.example.movie.common.base.adapter.BaseAdapter
import com.example.movie.common.utils.ImageLoader
import com.example.movie.databinding.ItemCastBinding
import com.example.movie.domain.model.cast_crew.CastMember

class CastMemberAdapter(
    private val onRootClick: ((CastMember) -> Unit)

    ): BaseAdapter<CastMember, ItemCastBinding>(emptyList()) {

    override fun createBinding(inflater: LayoutInflater, parent: ViewGroup): ItemCastBinding {
        return ItemCastBinding.inflate(inflater, parent, false)
    }

    override fun bind(binding: ItemCastBinding, item: CastMember, position: Int) {
        ImageLoader.loadImage2(binding.ivProfileImage, item.profileUrl)
        binding.tvActorName.text=item.name
        binding.characterOrName.text=item.character
        binding.root.setOnClickListener {
            onRootClick?.invoke(item)
        }


    }


}