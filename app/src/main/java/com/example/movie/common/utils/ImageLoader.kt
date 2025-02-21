package com.example.movie.common.utils

import android.content.Context
import android.widget.ImageView
import coil.load
import com.bumptech.glide.Glide
import androidx.annotation.DrawableRes
import com.squareup.picasso.Picasso

object ImageLoader {

    fun loadCircularImage(context: Context, imageUrl: String?, imageView: ImageView) {
        Glide.with(context)
            .load(imageUrl)
            .circleCrop()
            .into(imageView)
    }

    fun loadImage(
        imageView: ImageView,
        imageUrl: String?,
        placeholderResId: Int,
        errorResId: Int
    ) {
        imageUrl?.let {
            imageView.load(it) {
                placeholder(placeholderResId)
                error(errorResId)
            }
        }
    }
    fun loadImage2(
        imageView: ImageView,
        imageUrl: String?,
        @DrawableRes placeholder: Int = android.R.drawable.progress_indeterminate_horizontal,
        @DrawableRes errorImage: Int = android.R.drawable.stat_notify_error
    ) {
        Picasso.get()
            .load(imageUrl)
            .placeholder(placeholder)
            .error(errorImage)
            .into(imageView)
    }

}