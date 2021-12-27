package com.aibles.pstore.model.entities.dto

import com.google.gson.annotations.SerializedName

data class OrderResponse(
    @SerializedName("message") val message: String,
    @SerializedName("order_id") val orderId: Int
)