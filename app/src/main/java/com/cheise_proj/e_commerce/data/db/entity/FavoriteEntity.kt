package com.cheise_proj.e_commerce.data.db.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "favorite")
data class FavoriteEntity(
    @PrimaryKey(autoGenerate = true)
    val id: Int, var color: String, var size: String, var productId: String
) {
    constructor() : this(0, "Blue", "L", "")
}