package com.cheise_proj.e_commerce.data.repository

import com.cheise_proj.e_commerce.data.db.entity.AddressEntity
import kotlinx.coroutines.flow.Flow

interface IAddressRepository {
    suspend fun addAddress(addressEntity: AddressEntity)
    fun getAddresses(): Flow<List<AddressEntity>>
    fun getAddress(): Flow<AddressEntity>
    suspend fun updateAddress(addressEntity: AddressEntity)
    suspend fun removeAddress(identifier: Int?)
}