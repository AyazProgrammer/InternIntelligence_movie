package com.example.movie.presentation.adapter.movie_detail_page.cast_crew

import android.view.LayoutInflater
import android.view.ViewGroup
import com.example.movie.common.base.adapter.BaseAdapter
import com.example.movie.common.utils.ImageLoader
import com.example.movie.databinding.ItemCrewBinding
import com.example.movie.domain.model.cast_crew.CrewMember

class CrewMemberAdapter(
    private val onRootClick: ((CrewMember) -> Unit),

    ): BaseAdapter<CrewMember, ItemCrewBinding>(emptyList()) {

    override fun createBinding(inflater: LayoutInflater, parent: ViewGroup): ItemCrewBinding {
        return ItemCrewBinding.inflate(inflater, parent, false)
    }

    override fun bind(binding: ItemCrewBinding, item: CrewMember, position: Int) {
        ImageLoader.loadImage2(binding.ivProfileImage, item.profileUrl)
        binding.tvCrewName.text=item.name
        binding.crewRole.text=item.job
        binding.root.setOnClickListener {
            onRootClick?.invoke(item)
        }


    }


}