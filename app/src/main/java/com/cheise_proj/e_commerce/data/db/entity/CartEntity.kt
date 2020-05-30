package com.cheise_proj.e_commerce.data.db.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "cart")
data class CartEntity(
    @PrimaryKey(autoGenerate = true)
    val id: Int,
    var color: String,
    var size: String,
    var quantity: Int,
    var productId: String,
    var promoCode: String
) {
    constructor() : this(0, "Blue", "L", 1, "","")
}