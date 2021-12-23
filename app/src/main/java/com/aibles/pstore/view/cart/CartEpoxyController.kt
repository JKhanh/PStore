package com.aibles.pstore.view.cart

import com.aibles.pstore.CartBindingModel_
import com.aibles.pstore.model.entities.dto.ItemCartLocal
import com.aibles.pstore.model.entities.dto.ItemCartResponse
import com.airbnb.epoxy.TypedEpoxyController

class CartEpoxyController(
    private val listener: CartItemListener
): TypedEpoxyController<List<ItemCartLocal>>() {
    override fun buildModels(data: List<ItemCartLocal>?) {
        data?.forEach {
            CartBindingModel_()
                .id(it.productId)
                .item(it)
                .listener(listener)
                .addTo(this)
        }
    }

}