package com.aibles.pstore.data.repository

import com.aibles.pstore.data.remote.OrderService
import com.aibles.pstore.model.entities.dto.ItemCartRequest
import com.aibles.pstore.model.entities.dto.OrderResponse
import com.aibles.pstore.utils.remote.Resource
import javax.inject.Inject

class OrderRepositoryImpl @Inject constructor(
    private val service: OrderService
): OrderRepository {
    override suspend fun createOrder(items: List<ItemCartRequest>): Resource<OrderResponse> {
        return service.createOrder(items)
    }

    override suspend fun getListOrder() {
        TODO("Not yet implemented")
    }

    override suspend fun payOrder() {
        TODO("Not yet implemented")
    }
}