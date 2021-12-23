package com.aibles.pstore.model.entities.dto


import com.google.gson.annotations.SerializedName

data class AccountRegister(
    @SerializedName("email")
    val email: String,
    @SerializedName("password")
    val password: String,
    @SerializedName("profile")
    val profile: Profile
)