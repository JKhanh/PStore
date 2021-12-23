package com.aibles.pstore.view.home

import com.aibles.pstore.model.entities.Product

interface ProductOnClick {
    fun onClick(product: Product)
}