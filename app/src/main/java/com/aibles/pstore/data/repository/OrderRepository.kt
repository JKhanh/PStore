package com.aibles.pstore.data.repository

import com.aibles.pstore.model.entities.dto.ItemCartRequest
import com.aibles.pstore.model.entities.dto.OrderResponse
import com.aibles.pstore.utils.remote.Resource

interface OrderRepository {
    suspend fun createOrder(items: List<ItemCartRequest>): Resource<OrderResponse>
    suspend fun getListOrder()
    suspend fun payOrder()
}