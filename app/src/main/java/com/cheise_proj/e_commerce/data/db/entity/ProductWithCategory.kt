package com.cheise_proj.e_commerce.data.db.entity

import androidx.room.Embedded

data class ProductWithCategory(
    var categoryEntity: CategoryEntity
)