package com.aibles.pstore.view.home

import android.widget.ImageView
import androidx.databinding.BindingAdapter
import coil.load
import coil.size.Precision
import coil.size.Scale

@BindingAdapter("app:image")
fun loadPoster(imageView: ImageView, imageUrl: String) {
    imageView.load(imageUrl) {
        crossfade(true)
        precision(Precision.EXACT)
        scale(Scale.FILL)
    }
}