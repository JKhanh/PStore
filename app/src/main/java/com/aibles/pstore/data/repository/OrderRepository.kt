package com.aibles.pstore.data.repository

import com.aibles.pstore.model.entities.dto.ItemCartRequest

interface OrderRepository {
    suspend fun createOrder(items: List<ItemCartRequest>)
    suspend fun getListOrder()
    suspend fun payOrder()
}