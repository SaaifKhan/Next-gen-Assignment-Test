package com.saif.base.adapters

import android.widget.ImageView
import androidx.databinding.BindingAdapter
import coil.ImageLoader
import coil.load
import coil.request.ImageRequest
import com.saif.base.R


@BindingAdapter("imageUrl")
fun loadImage(imageView: ImageView, imageUrl: String?) {
    imageUrl?.let {
        imageView.load(it) {
            // You can customize image loading options here if needed
            crossfade(true) // Enable crossfade animation
            placeholder(R.drawable.baseline_cloud_download_24) // Placeholder image while loading
            error(R.drawable.baseline_running_with_errors_24) // Image to display in case of an error
        }
    }
}
