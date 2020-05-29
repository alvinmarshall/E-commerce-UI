package com.cheise_proj.e_commerce.data.db.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "address")
data class AddressEntity(
    @PrimaryKey(autoGenerate = true)
    val id: Int,
    var fullName: String,
    var address: String,
    var city: String,
    var state: String,
    var zipCode: String,
    var country: String,
    var isDefault: Int
) {
    constructor() : this(0, "", "", "", "", "", "", 0)
}