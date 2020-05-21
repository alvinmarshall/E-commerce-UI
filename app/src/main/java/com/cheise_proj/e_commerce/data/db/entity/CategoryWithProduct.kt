package com.cheise_proj.e_commerce.data.db.entity

import androidx.room.Embedded
import androidx.room.Relation

data class CategoryWithProduct(
    @Embedded
    val productEntity: ProductEntity,
    @Relation(parentColumn = "productID", entityColumn = "productId")
    val categoryEntity: CategoryEntity
)