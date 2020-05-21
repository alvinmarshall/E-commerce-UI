package com.cheise_proj.e_commerce.data.db.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "product")
data class ProductEntity(
    @PrimaryKey(autoGenerate = false)
    val productID: String,
    val productName: String,
    val supplierID: String,
    val categoryID: String,
    val quantityPerUnit: String,
    val unitPrice: String,
    val unitsInStock: String,
    val unitsOnOrder: String,
    val reorderLevel: String,
    val discontinued: String,
    val imageUrl: String,
    var favorite: Int = 0
) {
    constructor() : this("", "", "", "", "", "", "", "", "", "", "")
}