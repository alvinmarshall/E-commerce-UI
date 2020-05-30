package com.cheise_proj.e_commerce.utils

import com.cheise_proj.e_commerce.data.db.entity.CardEntity

object FakeCardService {

    fun getCards(): List<CardEntity> {
        return arrayListOf(
            CardEntity(
                id = 1,
                imageUrl = "any_image_url",
                type = "any_master_card",
                isDefault = 1,
                cardNumber = "any_card_number",
                cvv = "any_cvv",
                expiredDate = "any_expired_date",
                nameOnCard = "any_card_holder_name"
            ),
            CardEntity(
                id = 2,
                imageUrl = "any_image_url",
                type = "any_visa_card",
                isDefault = 1,
                cardNumber = "any_card_number",
                cvv = "any_cvv",
                expiredDate = "any_expired_date",
                nameOnCard = "any_card_holder_name"
            )
        )

    }


}