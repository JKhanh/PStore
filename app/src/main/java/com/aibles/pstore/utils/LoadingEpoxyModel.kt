package com.aibles.pstore.utils

import android.view.View
import com.aibles.pstore.R
import com.airbnb.epoxy.EpoxyHolder
import com.airbnb.epoxy.EpoxyModelClass
import com.airbnb.epoxy.EpoxyModelWithHolder
import com.facebook.shimmer.Shimmer
import com.facebook.shimmer.ShimmerFrameLayout

@EpoxyModelClass(layout = R.layout.loading_epoxy)
abstract class LoadingEpoxyModel: EpoxyModelWithHolder<LoadingEpoxyModel.Holder>() {
    class Holder : EpoxyHolder() {
        override fun bindView(itemView: View) {
            val shimmer = itemView.findViewById<ShimmerFrameLayout>(R.id.shimmer)
            shimmer.startShimmer()
        }
    }
}