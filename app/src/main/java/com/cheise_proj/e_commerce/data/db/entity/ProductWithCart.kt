package com.cheise_proj.e_commerce.data.db.entity

import androidx.room.Embedded

data class ProductWithCart(
    @Embedded
    val productEntity: ProductEntity?,
    @Embedded
    val cartEntity: CartEntity?
)