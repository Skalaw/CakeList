package com.rocksolidapps.cake.extensions

import android.view.View
import android.widget.ImageView
import androidx.databinding.BindingAdapter
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy

@BindingAdapter("imageUrl")
fun ImageView.loadPhoto(url: String?) {
    if (url == null) {
        visibility = View.GONE
    } else {
        visibility = View.VISIBLE
        Glide.with(this)
            .load(url)
            .diskCacheStrategy(DiskCacheStrategy.ALL)
            .into(this)
    }
}