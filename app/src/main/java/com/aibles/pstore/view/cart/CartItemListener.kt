package com.aibles.pstore.view.cart

import com.aibles.pstore.model.entities.dto.ItemCartLocal

interface CartItemListener {
    fun onItemCheckedChange(itemCartLocal: ItemCartLocal, isCheck: Boolean)
    fun increaseQuantity(itemCartLocal: ItemCartLocal)
    fun decreaseQuantity(itemCartLocal: ItemCartLocal)
}