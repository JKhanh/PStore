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
    suspend fun getAllProduct(): Resource<ProductResponse>

    @GET("products/")
    fun searchProduct(@Query("search") name: String): Resource<List<Product>>

    @GET("products/{id}/reviews")
    fun getProductReview(@Path("id") productId: Int): Resource<List<Review>>
}