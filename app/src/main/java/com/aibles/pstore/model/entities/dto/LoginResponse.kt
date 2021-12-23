package com.aibles.pstore.model.entities.dto


import com.google.gson.annotations.SerializedName

data class LoginResponse(
    @SerializedName("message")
    val message: String,
    @SerializedName("status_code")
    val statusCode: Int,
    @SerializedName("success")
    val success: String,
    @SerializedName("token")
    val token: String
)