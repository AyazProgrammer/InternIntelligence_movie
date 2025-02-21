package com.example.movie.presentation.adapter.cast_crew_page

import android.view.LayoutInflater
import android.view.ViewGroup
import com.example.movie.common.base.adapter.BaseAdapter
import com.example.movie.common.utils.ImageLoader
import com.example.movie.databinding.ItemActorBinding
import com.example.movie.domain.model.actors_movies.Actor

class ActorMemberAdapter (
    private val onRootClick: ((Actor) -> Unit)? = null,
): BaseAdapter<Actor, ItemActorBinding>(emptyList()) {

    override fun createBinding(inflater: LayoutInflater, parent: ViewGroup): ItemActorBinding {
        return ItemActorBinding.inflate(inflater, parent, false)
    }

    override fun bind(binding: ItemActorBinding, item: Actor, position: Int) {
        ImageLoader.loadImage2(binding.actorProfileImage, item.profileUrl)
        binding.actorName.text=item.name
        binding.actorOriginalName.text = "${item.originalName}"
        binding.actorPopularity.text = "Popularity: ${item.popularity}"
        binding.root.setOnClickListener {
            onRootClick?.invoke(item)
        }


    }


}