package com.cheise_proj.e_commerce.utils

import com.cheise_proj.e_commerce.data.db.entity.CartEntity
import com.cheise_proj.e_commerce.data.db.entity.ProductWithCart
import com.cheise_proj.e_commerce.extension.toEntity

object FakeCartService {
    fun getProductCart(): List<ProductWithCart> {
        return arrayListOf(
            ProductWithCart(
                cartEntity = getCart(),
                productEntity = FakeProductService.getProduct()[0].toEntity()
            )
        )

    }

    fun getCart(): CartEntity {
        return CartEntity(
            id = 1,
            quantity = 1,
            productId = "any_product_id",
            size = "any_size",
            color = "any_color",
            promoCode = "any_promo_code"
        )
    }
}