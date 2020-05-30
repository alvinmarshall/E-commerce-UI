package com.cheise_proj.e_commerce.data.db.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import com.cheise_proj.e_commerce.data.db.entity.OrderEntity
import com.cheise_proj.e_commerce.data.db.entity.Orders
import kotlinx.coroutines.flow.Flow

@Dao
interface OrderDao {
    @Insert
    fun addItem(orderEntity: OrderEntity)

    @Query("SELECT * FROM orders INNER JOIN address ON address.id = orders.addressId INNER JOIN card ON card.id = orders.cardId")
    fun getCheckoutInfo(): Flow<List<Orders>>

    @Query("DELETE FROM orders WHERE id = :identifier")
    fun removeCheckout(identifier: Int?)

    @Update
    fun updateCheckout(orderEntity: OrderEntity)

}