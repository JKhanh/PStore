package com.aibles.pstore.view.cart

import android.view.View
import com.aibles.pstore.R
import com.airbnb.epoxy.EpoxyHolder
import com.airbnb.epoxy.EpoxyModelClass
import com.airbnb.epoxy.EpoxyModelWithHolder

@EpoxyModelClass(layout = R.layout.cart_empty_epoxy)
abstract class CartEmptyEpoxyModel: EpoxyModelWithHolder<CartEmptyEpoxyModel.Holder>() {
    class Holder: EpoxyHolder(){
        override fun bindView(itemView: View) {
        }
    }
}