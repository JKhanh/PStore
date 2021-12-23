package com.aibles.pstore.view.order

import com.aibles.pstore.CartOrderBindingModel_
import com.aibles.pstore.model.entities.dto.ItemCartLocal
import com.airbnb.epoxy.TypedEpoxyController
import okhttp3.internal.addHeaderLenient
import timber.log.Timber

class OrderEpoxyController: TypedEpoxyController<List<ItemCartLocal>>() {
    override fun buildModels(data: List<ItemCartLocal>?) {
        data?.forEach {
            Timber.d("buildModels: $it")

            CartOrderBindingModel_()
                .id(it.id)
                .item(it)
                .addTo(this)
        }
    }
}