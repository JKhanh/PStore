package com.aibles.pstore.model.entities.dto

import com.google.gson.annotations.SerializedName

data class ItemCartRequest(
    @SerializedName("product_id") val productId: String = "null",
    @SerializedName("id") val itemId: Int = 0,
    @SerializedName("quantity") val quantity: Int = 1
)
