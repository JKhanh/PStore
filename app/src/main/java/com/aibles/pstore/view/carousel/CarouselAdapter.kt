package com.aibles.pstore.view.carousel

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import coil.load
import coil.size.Precision
import coil.size.Scale
import com.aibles.pstore.R
import com.asksira.loopingviewpager.LoopingPagerAdapter

class CarouselAdapter(
    itemList: List<String>,
    isInfinite: Boolean
): LoopingPagerAdapter<String>(itemList, isInfinite) {
    override fun bindView(convertView: View, listPosition: Int, viewType: Int) {
        val imageView = convertView.findViewById<ImageView>(R.id.view1)
        imageView.load(itemList?.get(listPosition)) {
            crossfade(true)
            precision(Precision.EXACT)
            scale(Scale.FILL)
        }
    }

    override fun inflateView(viewType: Int, container: ViewGroup, listPosition: Int): View {
        return LayoutInflater.from(container.context).inflate(R.layout.item_carousel, container, false)
    }
}