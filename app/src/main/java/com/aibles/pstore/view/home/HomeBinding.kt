package com.aibles.pstore.view.home

import android.widget.ImageView
import android.widget.TextView
import androidx.databinding.BindingAdapter
import coil.load
import coil.size.Precision
import coil.size.Scale
import com.aibles.pstore.utils.toTextPrice
import java.text.NumberFormat
import java.util.*

@BindingAdapter("app:image")
fun loadPoster(imageView: ImageView, imageUrl: String) {
    imageView.load(imageUrl) {
        crossfade(true)
        precision(Precision.EXACT)
        scale(Scale.FILL)
    }
}

@BindingAdapter("app:price")
fun setTextPrice(textView: TextView, price: Double){
    textView.text = price.toTextPrice()
}