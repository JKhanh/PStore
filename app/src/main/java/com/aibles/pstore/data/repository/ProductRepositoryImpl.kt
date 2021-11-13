package com.aibles.pstore.data.repository

import com.aibles.pstore.data.remote.ProductService
import javax.inject.Inject

class ProductRepositoryImpl@Inject constructor(private val productService: ProductService): ProductRepository {
    override suspend fun getAllProduct() =
        productService.getAllProduct()
}