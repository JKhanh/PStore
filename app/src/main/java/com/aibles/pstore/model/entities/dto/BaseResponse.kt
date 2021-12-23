package com.aibles.pstore.model.entities.dto

import com.google.gson.annotations.SerializedName

data class BaseResponse(
    @SerializedName("message") val message: String
)
