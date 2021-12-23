package com.aibles.pstore.data.remote

import com.aibles.pstore.model.entities.Product
import com.aibles.pstore.model.entities.Review
import com.aibles.pstore.model.entities.dto.ProductResponse
import com.aibles.pstore.utils.remote.Resource
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface ProductService {
    @GET("products/")
    suspend fun getAllProduct(@Query("offset") offset: Int = 0): Resource<ProductResponse>

    @GET("products/")
    fun searchProduct(@Query("search") name: String): Resource<List<Product>>

    @GET("recommend/{id}")
    suspend fun getProductRecommend(@Path("id") productId: String): Resource<List<Product>>
}