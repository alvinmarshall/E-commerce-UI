package com.cheise_proj.e_commerce.data.service

import com.cheise_proj.e_commerce.model.ProfileSection
import com.cheise_proj.e_commerce.ui.profile.adapter.ProfileAdapter

object ProfileSectionService {
    fun getProfileTitles(): List<ProfileSection> {
        return arrayListOf(
            ProfileSection(
                title = "My orders",
                viewType = ProfileAdapter.ORDERS_VIEW
            ), ProfileSection(
                title = "Shipping addresses",
                viewType = ProfileAdapter.ADDRESS_VIEW
            ), ProfileSection(
                title = "Payment methods",
                viewType = ProfileAdapter.PAYMENT_VIEW
            ), ProfileSection(
                title = "Promo codes",
                viewType = ProfileAdapter.PROMO_CODE_VIEW
            ), ProfileSection(
                title = "Settings",
                viewType = ProfileAdapter.SETTINGS_VIEW
            )
        )

    }
}