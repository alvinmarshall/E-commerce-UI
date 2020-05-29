package com.cheise_proj.e_commerce.utils

import com.cheise_proj.e_commerce.data.db.entity.AddressEntity

object FakeAddressService {
    fun getAddresses(): List<AddressEntity> {
        return arrayListOf(
            AddressEntity(
                id = 1,
                fullName = "any_full_name",
                address = "any_address",
                city = "any_city",
                country = "any_country",
                isDefault = 1,
                state = "any_state",
                zipCode = "any_zip_code"
            ),

            AddressEntity(
                id = 2,
                fullName = "any_full_name2",
                address = "any_address2",
                city = "any_city2",
                country = "any_country2",
                isDefault = 0,
                state = "any_state2",
                zipCode = "any_zip_code2"
            )
        )

    }
}