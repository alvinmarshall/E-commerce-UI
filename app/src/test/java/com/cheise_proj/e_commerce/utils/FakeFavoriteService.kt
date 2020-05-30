package com.cheise_proj.e_commerce.utils

import com.cheise_proj.e_commerce.data.db.entity.FavoriteEntity
import com.cheise_proj.e_commerce.data.db.entity.ProductWithFavorite
import com.cheise_proj.e_commerce.extension.toEntity

object FakeFavoriteService {
    fun getFavorite(): FavoriteEntity {
        return FavoriteEntity(
            id = 1,
            color = "any_color",
            size = "any_size",
            productId = "any_product_id"
        )
    }

    fun getProductWithFavorite(): List<ProductWithFavorite> {
        return arrayListOf(
            ProductWithFavorite(
                productEntity = FakeProductService.getProduct()[0].toEntity(),
                favoriteEntity = getFavorite()
            )
        )
    }
}