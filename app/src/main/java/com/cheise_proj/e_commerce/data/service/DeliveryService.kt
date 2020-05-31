package com.cheise_proj.e_commerce.data.service

import com.cheise_proj.e_commerce.data.db.entity.DeliveryEntity
import java.util.*

object DeliveryService {
    const val DELIVERED = 1
    const val PROCESSING = 0
    const val CANCELLED = 2
    fun getDeliveryTypes(): List<DeliveryEntity> {
        return arrayListOf(
            DeliveryEntity(
                id = 1,
                name = "FedEx",
                imageUrl = "https://aibusiness.com/wp-content/uploads/2017/09/fedex-main.png",
                duration = "2-3 days",
                cost = 15
            ),
            DeliveryEntity(
                id = 2,
                name = "USPS.COM",
                imageUrl = "https://d1yjjnpx0p53s8.cloudfront.net/styles/logo-thumbnail/s3/052018/untitled-1_365.png?us67Stpa2P0RQNUJp1qNIrEtCEuWkjLK&itok=Moe-idUs",
                duration = "2-3 days",
                cost = 20
            ),


            DeliveryEntity(
                id = 3,
                name = "DHL",
                imageUrl = "https://logistyx.com/wp-content/uploads/2019/09/DHL-logo.png",
                duration = "2-3 days",
                cost = 12
            )
        )

    }

    fun getDeliveryTracker(): String {
        return UUID.randomUUID().toString().substring(0, 12)
    }

}