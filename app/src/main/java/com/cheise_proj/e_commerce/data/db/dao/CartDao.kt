package com.cheise_proj.e_commerce.data.db.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import com.cheise_proj.e_commerce.data.db.entity.CartEntity
import com.cheise_proj.e_commerce.data.db.entity.ProductWithCart
import kotlinx.coroutines.flow.Flow

@Dao
interface CartDao {
    @Insert
    fun addToCart(cartEntity: CartEntity)

    @Query("SELECT * FROM product INNER JOIN cart ON cart.productId = product.productID")
    fun getProductCart(): Flow<List<ProductWithCart>>

    @Query("DELETE FROM cart WHERE id = :identifier")
    fun removeCart(identifier: Int?)

    @Update
    fun updateCart(cartEntity: CartEntity)

}