package com.aibles.pstore.model.entities.dto

import com.aibles.pstore.model.entities.Product

data class ProductResponse(
    val results: List<Product>
)
