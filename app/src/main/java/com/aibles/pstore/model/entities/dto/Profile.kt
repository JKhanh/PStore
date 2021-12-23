package com.aibles.pstore.model.entities.dto


import com.google.gson.annotations.SerializedName

data class Profile(
    @SerializedName("birth_date")
    val birthDate: Any?,
    @SerializedName("first_name")
    val firstName: String,
    @SerializedName("gender")
    val gender: Any?,
    @SerializedName("last_name")
    val lastName: String,
    @SerializedName("phone_number")
    val phoneNumber: String
)