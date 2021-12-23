package com.aibles.pstore.view.home

import com.aibles.pstore.ProductBindingModel_
import com.aibles.pstore.ProductRecommendBindingModel_
import com.aibles.pstore.model.entities.Product
import com.airbnb.epoxy.TypedEpoxyController

class RecommendEpoxyController(
    private val onClick: ProductOnClick
): TypedEpoxyController<List<Product>>() {
    override fun buildModels(data: List<Product>?) {
        data?.forEach {
            ProductRecommendBindingModel_()
                .id(it.id)
                .product(it)
                .onClick(onClick)
                .addTo(this)
        }
    }
}