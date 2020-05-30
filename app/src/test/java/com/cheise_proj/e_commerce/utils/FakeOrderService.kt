package com.cheise_proj.e_commerce.utils

import com.cheise_proj.e_commerce.data.db.entity.OrderEntity
import com.cheise_proj.e_commerce.data.db.entity.Orders

object FakeOrderService {
    fun getOrders(): List<Orders> {
        return arrayListOf(
            Orders(
                cardEntity = FakeCardService.getCards()[0],
                addressEntity = FakeAddressService.getAddresses()[0],
                orderEntity = getOrder()
            )
        )
    }

    fun getOrder(): OrderEntity {
        return OrderEntity(
            id = 1,
            tracker = "any_uid",
            date = "any_date_time",
            status = 1,
            delivery = FakeDeliveryService.getDeliveryTypes()[0],
            cardId = FakeCardService.getCards()[0].id,
            addressId = FakeAddressService.getAddresses()[0].id,
            quantity = 1,
            total = "any_order_count"
        )
    }
}