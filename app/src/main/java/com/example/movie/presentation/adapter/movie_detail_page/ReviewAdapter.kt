package com.example.movie.presentation.adapter.movie_detail_page

import android.view.LayoutInflater
import android.view.ViewGroup
import com.example.movie.common.base.adapter.BaseAdapter
import com.example.movie.common.utils.ImageLoader
import com.example.movie.databinding.ItemReviewBinding
import com.example.movie.domain.model.review.UserReview

class ReviewAdapter (

): BaseAdapter<UserReview, ItemReviewBinding>(emptyList()) {

    override fun createBinding(inflater: LayoutInflater, parent: ViewGroup): ItemReviewBinding {
        return ItemReviewBinding.inflate(inflater, parent, false)
    }

    override fun bind(binding: ItemReviewBinding, item: UserReview, position: Int) {
       ImageLoader.loadImage2(binding.imageView, item.author_details.avatarUrl)
         binding.authorName.text = item.author
         binding.content.text =item.content
         binding.reviewPoint.text = item.author_details.rating.toString()


    }


}