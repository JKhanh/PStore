package com.aibles.pstore.model.entities.dto

import com.google.gson.annotations.SerializedName

data class ItemCartResponse(
    @SerializedName("id") val id: Int,
    @SerializedName("product_id") val productId: String,
    @SerializedName("product_name") val productName: String,
    @SerializedName("product_price") val productPrice: Double,
    @SerializedName("product_image") val productImage: String,
    @SerializedName("quantity") val quantity: Int,
){
    fun mapToLocal(): ItemCartLocal =
        ItemCartLocal(id, productId, productName, productImage, productPrice, quantity)
}
