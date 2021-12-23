package com.aibles.pstore.view.cart

import com.aibles.pstore.CartBindingModel_
import com.aibles.pstore.model.entities.dto.ItemCartLocal
import com.aibles.pstore.model.entities.dto.ItemCartResponse
import com.airbnb.epoxy.TypedEpoxyController

class CartEpoxyController(
    private val listener: CartItemListener
): TypedEpoxyController<List<ItemCartLocal>>() {
    override fun buildModels(data: List<ItemCartLocal>?) {
        data?.let {
            if (it.isEmpty()){
                CartEmptyEpoxyModel_()
                    .id("empty")
                    .addTo(this)
            } else {
                data.forEach { item ->
                    CartBindingModel_()
                        .id(item.productId)
                        .item(item)
                        .listener(listener)
                        .addTo(this)
                }
            }
        }

    }

}