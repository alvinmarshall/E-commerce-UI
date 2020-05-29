package com.cheise_proj.e_commerce.data.repository

import com.cheise_proj.e_commerce.data.db.entity.CartEntity
import com.cheise_proj.e_commerce.data.db.entity.ProductWithCart
import kotlinx.coroutines.flow.Flow

interface ICartRepository {
    suspend fun addToCart(cartEntity: CartEntity)
    fun getProductCart(): Flow<List<ProductWithCart>>
    suspend fun removeCart(identifier: Int?)
    suspend fun updateCartQuantity(cartEntity: CartEntity)
}