package com.aibles.pstore.view.cart

import androidx.lifecycle.*
import com.aibles.pstore.data.repository.CartRepository
import com.aibles.pstore.model.entities.Product
import com.aibles.pstore.model.entities.dto.ItemCartLocal
import com.aibles.pstore.model.entities.dto.ItemCartResponse
import com.aibles.pstore.utils.AppDispatchers
import com.aibles.pstore.utils.remote.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.async
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.launch
import timber.log.Timber
import javax.inject.Inject

@HiltViewModel
class CartViewModel @Inject constructor(
    private val repository: CartRepository,
    private val dispatchers: AppDispatchers
): ViewModel() {
    private var _cart: LiveData<Resource<List<ItemCartLocal>>> = repository.getCart()
    val cart: LiveData<Resource<List<ItemCartLocal>>> get() = _cart

    val orderItems = mutableListOf<ItemCartLocal>()

    fun addToCart(product: Product){
        viewModelScope.launch(dispatchers.io){
            repository.addToCart(product)
        }
    }

    fun getCart(){
        Timber.d("getCart: ViewModel")
        viewModelScope.launch(dispatchers.main){
            _cart = repository.getCart()
        }
    }

    fun addToOrder(itemCartLocal: ItemCartLocal, isCheck: Boolean){
        if (isCheck){
            orderItems.add(itemCartLocal)
        } else {
            orderItems.remove(itemCartLocal)
        }
    }

    fun updateQuantity(itemCartLocal: ItemCartLocal, quantity: Int){
        viewModelScope.launch(dispatchers.io){
            repository.updateQuantity(itemCartLocal, quantity)
        }
    }

    fun deleteAfterOrder() {
        viewModelScope.launch(dispatchers.io) {
            repository.deleteItem(orderItems)
        }
    }
}