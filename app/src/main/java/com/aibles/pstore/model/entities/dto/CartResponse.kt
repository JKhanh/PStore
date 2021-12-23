package com.aibles.pstore.model.entities.dto

import com.google.gson.annotations.SerializedName

data class CartResponse(
    @SerializedName("items") val items: List<ItemCartResponse>
)
