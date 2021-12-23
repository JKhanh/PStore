package com.aibles.pstore.utils

import android.view.View
import android.widget.TextView
import com.aibles.pstore.R
import com.airbnb.epoxy.EpoxyAttribute
import com.airbnb.epoxy.EpoxyHolder
import com.airbnb.epoxy.EpoxyModelClass
import com.airbnb.epoxy.EpoxyModelWithHolder

@EpoxyModelClass(layout = R.layout.error_epoxy)
abstract class ErrorEpoxyModel: EpoxyModelWithHolder<ErrorEpoxyModel.Holder>() {

    class Holder : EpoxyHolder() {
        override fun bindView(itemView: View) {
        }
    }
}