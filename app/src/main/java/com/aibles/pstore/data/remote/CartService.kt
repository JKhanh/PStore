package com.aibles.pstore.data.remote

import com.aibles.pstore.model.entities.dto.BaseResponse
import com.aibles.pstore.model.entities.dto.CartResponse
import com.aibles.pstore.model.entities.dto.ItemCartRequest
import com.aibles.pstore.model.entities.dto.ItemCartResponse
import com.aibles.pstore.utils.remote.Resource
import retrofit2.http.*

interface CartService {
    @POST("cart/")
    suspend fun addToCart(@Body itemCartRequest: ItemCartRequest): Resource<BaseResponse>

    @GET("cart/")
    suspend fun getCart(): Resource<CartResponse>

    @HTTP(method = "DELETE", path = "cart/", hasBody = true)
    suspend fun deleteItem(@Body itemCartRequest: ItemCartRequest): Resource<BaseResponse>

    @PUT("cart/")
    suspend fun updateItemCart(@Body itemCartRequest: ItemCartRequest): Resource<BaseResponse>
}