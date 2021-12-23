package com.aibles.pstore.data.repository

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.aibles.pstore.data.remote.ProductService
import com.aibles.pstore.model.entities.Product
import com.aibles.pstore.model.entities.dto.ProductResponse
import com.aibles.pstore.utils.remote.Resource
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

const val PAGING_LIMIT = 50
class ProductRepositoryImpl @Inject constructor(private val productService: ProductService): ProductRepository {
    private val paging3Config = PagingConfig(pageSize = PAGING_LIMIT, enablePlaceholders = false)

    override fun getAllProductPaging(): Flow<PagingData<Product>> {
        return Pager(config = paging3Config){
            ProductDataSource(productService)
        }.flow
    }

    override suspend fun getAllProduct(): Resource<ProductResponse> {
        return productService.getAllProduct()
    }

    override suspend fun getRecommend(productId: String): Resource<List<Product>> {
        return productService.getProductRecommend(productId)
    }
}