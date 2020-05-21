package com.cheise_proj.e_commerce.data.db.entity

import androidx.room.*
import androidx.room.ForeignKey.CASCADE
import com.cheise_proj.e_commerce.model.Product

@Entity(
    tableName = "category"
)

data class CategoryEntity(
    @PrimaryKey(autoGenerate = false)
    val categoryID: String,
    val categoryName: String,
    val description: String,
    val imageUrl: String,
    var productId: Int = 0
) {
    constructor() : this("", "", "", "")

    @Ignore
    var product: List<Product> = emptyList()
}