package com.aibles.pstore.data.repository

import androidx.lifecycle.LiveData
import com.aibles.pstore.model.entities.Product
import com.aibles.pstore.model.entities.dto.BaseResponse
import com.aibles.pstore.model.entities.dto.ItemCartLocal
import com.aibles.pstore.model.entities.dto.ItemCartResponse
import com.aibles.pstore.utils.remote.Resource
import kotlinx.coroutines.flow.Flow

interface CartRepository {
    suspend fun addToCart(product: Product, quantity: Int = 1)
    fun getCart(): LiveData<Resource<List<ItemCartLocal>>>
    suspend fun updateQuantity(itemCartLocal: ItemCartLocal, quantity: Int)
    suspend fun deleteItem(itemCartLocal: MutableList<ItemCartLocal>)
}