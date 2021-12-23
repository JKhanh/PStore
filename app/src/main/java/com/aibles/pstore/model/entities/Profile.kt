package com.aibles.pstore.model.entities


import com.google.gson.annotations.SerializedName

data class Profile(
    @SerializedName("address")
    val address: String,
    @SerializedName("birth_date")
    val birthDate: String,
    @SerializedName("first_name")
    val firstName: String,
    @SerializedName("gender")
    val gender: String,
    @SerializedName("last_name")
    val lastName: String,
    @SerializedName("phone_number")
    val phoneNumber: String
)