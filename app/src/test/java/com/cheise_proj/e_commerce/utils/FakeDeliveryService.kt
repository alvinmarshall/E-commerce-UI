package com.cheise_proj.e_commerce.utils

import com.cheise_proj.e_commerce.data.db.entity.DeliveryEntity

object FakeDeliveryService {

    fun getDeliveryTypes(): List<DeliveryEntity> {
        return arrayListOf(
            DeliveryEntity(
                id = 1,
                imageUrl = "any_image_url",
                cost = 1,
                name = "any_name",
                duration = "any_duration_time"
            )
        )
    }
}