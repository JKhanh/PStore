package com.aibles.pstore.data.local

import androidx.lifecycle.LiveData
import androidx.room.*
import com.aibles.pstore.model.entities.dto.ItemCartLocal
import kotlinx.coroutines.flow.Flow

@Dao
interface CartDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun addToCart(item: ItemCartLocal)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun addToCart(items: List<ItemCartLocal>)

    @Query("SELECT * FROM itemcartlocal ORDER BY updated DESC")
    fun getCart(): LiveData<List<ItemCartLocal>>

    @Query("UPDATE itemcartlocal SET quantity =:quantity, updated =:updated" +
            " WHERE productId =:itemId")
    suspend fun updateItem(itemId: String, quantity: Int, updated: Long = System.currentTimeMillis())

    @Delete
    suspend fun deleteItem(item: ItemCartLocal)

    suspend fun updateQuantity(item: ItemCartLocal, quantity: Int){
        if(quantity > 0){
            updateItem(item.productId, item.quantity + 1)
        } else {
            if(item.quantity == 1) {
                deleteItem(item)
            } else {
                updateItem(item.productId, item.quantity - 1)
            }
        }
    }
}