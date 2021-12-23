package com.aibles.pstore.model.entities

import com.google.gson.annotations.SerializedName

data class Account(
    @SerializedName("email") val username: String,
    @SerializedName("password") val password: String
)
