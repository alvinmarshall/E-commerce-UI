package com.cheise_proj.e_commerce.data.db.entity

import androidx.room.Embedded

data class ProductWithFavorite(
    @Embedded
    val productEntity: ProductEntity,
    @Embedded
    val favoriteEntity: FavoriteEntity?

)