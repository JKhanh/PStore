package com.aibles.pstore.model.entities.dto

import androidx.room.Entity
import androidx.room.PrimaryKey
import java.util.*

@Entity
data class ItemCartLocal(
    var id: Int = 0,
    @PrimaryKey val productId: String,
    val productName: String,
    val productImage: String,
    val productPrice: Double,
    var quantity: Int,
    val created: Long = System.currentTimeMillis(),
    var updated: Long = System.currentTimeMillis()
)
