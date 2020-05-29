package com.cheise_proj.e_commerce.data.service

import com.cheise_proj.e_commerce.model.PromoCode

object PromoService {
    fun getPromos(): List<PromoCode> {
        return arrayListOf(
            PromoCode(
                id = 1,
                name = "Personal offer",
                title = "mypromocode200",
                elapsedTime = "6 days remaining",
                imageUrl = "https://as1.ftcdn.net/jpg/02/25/89/34/500_F_225893431_04g6YItFhNkAE0MdNwCLwFJqd8qBvtVl.jpg"
            ),
            PromoCode(
                id = 2,
                name = "Summer offer",
                title = "mysummercode3021",
                elapsedTime = "21 days remaining",
                imageUrl = "https://i.pinimg.com/564x/25/1a/6d/251a6dbcf7756c44613bda50595e6dcb.jpg"
            ),
            PromoCode(
                id = 3,
                name = "Personal offer",
                title = "mypersonalcode430",
                elapsedTime = "1 day remaining",
                imageUrl = "https://image.freepik.com/free-photo/20-off-white-background-special-offer-great-offer-sale-twenty-percent-off-promotional_59529-689.jpg"
            )
        )

    }
}