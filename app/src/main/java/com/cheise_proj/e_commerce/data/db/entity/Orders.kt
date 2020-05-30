package com.cheise_proj.e_commerce.data.db.entity

import androidx.room.Embedded

data class Orders(
    @Embedded
    val orderEntity: OrderEntity?,
    @Embedded(prefix = "address")
    val addressEntity: AddressEntity?,
    @Embedded(prefix = "card")
    val cardEntity: CardEntity?
)