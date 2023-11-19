package com.rootdown.dev.paging_v3_1.util

import android.widget.ImageView
import androidx.databinding.BindingAdapter
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy

/**
 * Binding adapter used to display images from URL using Glide
 */
@BindingAdapter("imageUrl")
fun setImageUrl(imageView: ImageView, url: String) {
    Glide.with(imageView.context)
        .load("https://d3b3tm7jjqus71.cloudfront.net/$url")
        .diskCacheStrategy(DiskCacheStrategy.ALL)
        .into(imageView)
}