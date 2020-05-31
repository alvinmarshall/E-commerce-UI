package com.cheise_proj.e_commerce.data.db.entity

import androidx.room.Embedded
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "orders")
data class OrderEntity(
    @PrimaryKey(autoGenerate = true)
    val id: Int,
    var total: String,
    val quantity: Int,
    var addressId: Int,
    var cardId: Int,
    @Embedded(prefix = "delivery")
    var delivery: DeliveryEntity?,
    var status: Int = 0,
    val date: String,
    var tracker: String,
    var promoCode:String?
)