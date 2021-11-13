package com.aibles.pstore.view.home

import com.aibles.pstore.ProductBindingModel_
import com.aibles.pstore.model.entities.Product
import com.airbnb.epoxy.TypedEpoxyController
import timber.log.Timber

class ProductEpoxyController: TypedEpoxyController<List<Product>>() {
    override fun buildModels(data: List<Product>?) {
        data?.forEach {
            Timber.d("buildModels: $it")
            ProductBindingModel_()
                .id(it.id)
                .product(it)
                .addTo(this)
        }
    }
}