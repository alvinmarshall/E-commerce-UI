package com.cheise_proj.e_commerce.model

data class Product(
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
    val imageUrl: String
)