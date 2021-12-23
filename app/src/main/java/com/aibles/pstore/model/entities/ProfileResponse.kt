package com.aibles.pstore.model.entities


import com.google.gson.annotations.SerializedName

data class ProfileResponse(
    @SerializedName("data")
    val profile: List<Profile>,
    @SerializedName("message")
    val message: String,
    @SerializedName("status_code")
    val statusCode: Int,
    @SerializedName("success")
    val success: String
)