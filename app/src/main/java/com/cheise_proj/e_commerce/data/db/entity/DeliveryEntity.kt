package com.cheise_proj.e_commerce.data.db.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "delivery")
data class DeliveryEntity(
    @PrimaryKey(autoGenerate = false)
    val id: Int,
    val name: String,
    val duration: String,
    val imageUrl: String,
    val cost:Int
)