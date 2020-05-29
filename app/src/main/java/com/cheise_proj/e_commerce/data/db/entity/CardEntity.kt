package com.cheise_proj.e_commerce.data.db.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "card")
data class CardEntity(
    @PrimaryKey(autoGenerate = true)
    val id: Int,
    var nameOnCard: String,
    var cardNumber: String,
    var expiredDate: String,
    var cvv: String,
    var isDefault: Int,
    var type: String,
    var imageUrl: String
) {
    constructor() : this(0, "", "", "", "", 0, "master","")
}