package com.cheise_proj.e_commerce.data.repository

import com.cheise_proj.e_commerce.data.db.dao.CartDao
import com.cheise_proj.e_commerce.data.db.entity.CartEntity
import com.cheise_proj.e_commerce.data.db.entity.ProductWithCart
import kotlinx.coroutines.flow.Flow
import timber.log.Timber
import javax.inject.Inject

class CartRepository @Inject constructor(private val cartDao: CartDao) : ICartRepository {
    override suspend fun addToCart(cartEntity: CartEntity) {
        Timber.i("addToCart: $cartEntity")
        cartDao.addToCart(cartEntity)
    }

    override fun getProductCart(): Flow<List<ProductWithCart>> = cartDao.getProductCart()

    override suspend fun removeCart(identifier: Int?) {
        Timber.i("removeCart with identifier: $identifier")
        cartDao.removeCart(identifier)
    }

    override suspend fun updateCartQuantity(cartEntity: CartEntity) {
        Timber.i("updateCartQuantity: $cartEntity")
        cartDao.updateCartQuantity(cartEntity)
    }
}