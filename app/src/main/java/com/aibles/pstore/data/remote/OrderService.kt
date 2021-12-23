package com.aibles.pstore.data.remote

import com.aibles.pstore.model.entities.dto.ItemCartRequest
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST

interface OrderService {
    @POST("order/")
    suspend fun createOrder(@Body items: List<ItemCartRequest>)

    @GET("order/")
    suspend fun getOrderList()

    @POST("payment/")
    suspend fun payOrder()
}