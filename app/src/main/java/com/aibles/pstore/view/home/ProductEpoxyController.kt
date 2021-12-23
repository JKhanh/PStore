package com.aibles.pstore.view.home

import com.aibles.pstore.ProductBindingModel_
import com.aibles.pstore.model.entities.Product
import com.aibles.pstore.utils.ErrorEpoxyModel_
import com.aibles.pstore.utils.LoadingEpoxyModel_
import com.airbnb.epoxy.TypedEpoxyController
import timber.log.Timber

class ProductEpoxyController(
    private val onClick: ProductOnClick
): TypedEpoxyController<List<Product>>() {
    var isError = false
        set(value) {
            field = value
            if (field) {
                setData(listOf())
            }
        }

    var isLoading = false
        set(value) {
            field = value
            if (field) {
                setData(listOf())
            }
        }

    override fun buildModels(data: List<Product>?) {
        if (isLoading) {
            repeat(10) {
                LoadingEpoxyModel_()
                    .id("loading")
                    .addTo(this)
            }
        } else if(isError){
            ErrorEpoxyModel_()
                .id("error")
                .spanSizeOverride { _, _, _ -> 2 }
                .addTo(this)
        }
        else {
            data?.forEach {
                ProductBindingModel_()
                    .id(it.id)
                    .product(it)
                    .onClick(onClick)
                    .addTo(this)
            }
        }
    }
}