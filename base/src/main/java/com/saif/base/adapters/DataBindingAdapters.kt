package com.saif.base.adapters

import android.widget.ImageView
import androidx.databinding.BindingAdapter
import coil.load
import com.saif.base.R


@BindingAdapter("imageUrl")
fun loadImage(imageView: ImageView, imageUrl: String?) {
    imageUrl?.let {
        imageView.load(it) {
            // You can customize image loading options here if needed
            crossfade(true) // Enable crossfade animation
            placeholder(R.drawable.error) // Placeholder image while loading
            error(R.drawable.error) // Image to display in case of an error
        }
    }
}