package com.cheise_proj.e_commerce.model

data class Category(
    val categoryID: String,
    val categoryName: String,
    val description: String,
    val imageUrl: String,
    var product: List<Product>
)