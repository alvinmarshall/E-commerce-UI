package com.cheise_proj.e_commerce.data.service

import com.cheise_proj.e_commerce.model.Checkout
import com.cheise_proj.e_commerce.ui.bag.adapter.CheckoutAdapter.Companion.DELIVERY_VIEW
import com.cheise_proj.e_commerce.ui.bag.adapter.CheckoutAdapter.Companion.PAYMENT_VIEW
import com.cheise_proj.e_commerce.ui.bag.adapter.CheckoutAdapter.Companion.SHIPPING_VIEW

object CheckoutService {
    fun getCheckoutTitles(): List<Checkout> {
        return arrayListOf(
            Checkout(
                title = "Shipping Address",
                viewType = SHIPPING_VIEW
            ),
            Checkout(
                title = "Payment",
                viewType = PAYMENT_VIEW
            ),
            Checkout(
                title = "Delivery Method",
                viewType = DELIVERY_VIEW
            )
        )

    }
}