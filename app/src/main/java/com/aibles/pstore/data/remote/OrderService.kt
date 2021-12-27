package com.aibles.pstore.data.remote

import com.aibles.pstore.model.entities.dto.ItemCartRequest
import com.aibles.pstore.model.entities.dto.OrderResponse
import com.aibles.pstore.utils.remote.Resource
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST

interface OrderService {
    @POST("order/")
    suspend fun createOrder(@Body items: List<ItemCartRequest>): Resource<OrderResponse>

    @GET("order/")
    suspend fun getOrderList()

    @POST("payment/")
    suspend fun payOrder()
}