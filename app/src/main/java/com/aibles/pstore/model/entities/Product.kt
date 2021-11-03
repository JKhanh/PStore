package com.aibles.pstore.model.entities

data class Product(
    val name: String,
    val basePrice: Double,
    val sale: Float,
    val image: String
){
    fun getCurrentPrice(): String =
        (basePrice*(100-sale)).toString()
}
