package com.aibles.pstore.data.repository

import androidx.lifecycle.LiveData
import com.aibles.pstore.data.local.CartDao
import com.aibles.pstore.data.remote.CartService
import com.aibles.pstore.model.entities.Product
import com.aibles.pstore.model.entities.dto.BaseResponse
import com.aibles.pstore.model.entities.dto.ItemCartLocal
import com.aibles.pstore.model.entities.dto.ItemCartRequest
import com.aibles.pstore.model.entities.dto.ItemCartResponse
import com.aibles.pstore.utils.AppDispatchers
import com.aibles.pstore.utils.networkBoundResource
import com.aibles.pstore.utils.performGetOperation
import com.aibles.pstore.utils.remote.Resource
import com.orhanobut.hawk.Hawk
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.flow.map
import timber.log.Timber
import javax.inject.Inject

class CartRepositoryImpl @Inject constructor(
    private val cartService: CartService,
    private val cartDao: CartDao,
) : CartRepository {
    override suspend fun addToCart(product: Product, quantity: Int) {
        if (Hawk.contains("token"))
            cartService.addToCart(ItemCartRequest(product.id))
        cartDao.addToCart(
            ItemCartLocal(
                productId = product.id,
                productName = product.name,
                productImage = product.image,
                productPrice = product.basePrice,
                quantity = quantity
            )
        )
    }

    override fun getCart(): LiveData<Resource<List<ItemCartLocal>>> {
        return performGetOperation(
            databaseQuery = {
                cartDao.getCart()
            },
            networkCall = {
                cartService.getCart()
            },
            saveCallResult = {
                cartDao.addToCart(it.items.map { it.mapToLocal() })
            }
        )
    }

    override suspend fun updateQuantity(itemCartLocal: ItemCartLocal, quantity: Int) {
        cartDao.updateQuantity(itemCartLocal, quantity)
        if (quantity > 0) {
            cartService.updateItemCart(
                ItemCartRequest(
                    itemId = itemCartLocal.id,
                    quantity = itemCartLocal.quantity + 1
                )
            )
        } else {
            if (itemCartLocal.quantity == 1) {
                deleteItem(itemCartLocal)
            } else {
                cartService.updateItemCart(
                    ItemCartRequest(
                        itemId = itemCartLocal.id,
                        quantity = itemCartLocal.quantity - 1
                    )
                )
            }
        }
    }

    private suspend fun deleteItem(itemCartLocal: ItemCartLocal) {
        if (Hawk.contains("token")) {
            cartService.deleteItem(ItemCartRequest(itemId = itemCartLocal.id))
        }
    }

    override suspend fun deleteItem(itemCartLocal: MutableList<ItemCartLocal>) {
        cartDao.deleteListItem(itemCartLocal.map { it.id })
    }
}