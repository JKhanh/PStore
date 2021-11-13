package com.aibles.pstore.data.repository

import com.aibles.pstore.data.remote.ProductService
import com.aibles.pstore.model.entities.Product
import com.aibles.pstore.model.entities.dto.ProductResponse
import com.aibles.pstore.utils.remote.Resource
import javax.inject.Inject

interface ProductRepository  {
    suspend fun getAllProduct(): Resource<ProductResponse>
}