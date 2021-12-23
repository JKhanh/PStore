package com.aibles.pstore.data.repository

import androidx.paging.PagingData
import com.aibles.pstore.data.remote.ProductService
import com.aibles.pstore.model.entities.Product
import com.aibles.pstore.model.entities.dto.ProductResponse
import com.aibles.pstore.utils.remote.Resource
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

interface ProductRepository  {
    suspend fun getAllProduct(): Resource<ProductResponse>
    suspend fun getRecommend(productId: String): Resource<List<Product>>
    fun getAllProductPaging(): Flow<PagingData<Product>>
}