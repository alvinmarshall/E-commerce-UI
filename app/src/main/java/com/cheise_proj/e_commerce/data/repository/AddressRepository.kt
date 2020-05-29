package com.cheise_proj.e_commerce.data.repository

import com.cheise_proj.e_commerce.data.db.dao.AddressDao
import com.cheise_proj.e_commerce.data.db.entity.AddressEntity
import kotlinx.coroutines.flow.Flow
import timber.log.Timber
import javax.inject.Inject

class AddressRepository @Inject constructor(private val addressDao: AddressDao) :
    IAddressRepository {
    override suspend fun addAddress(addressEntity: AddressEntity) {
        Timber.i("addAddress: $addressEntity")
        addressDao.addAddress(addressEntity)
    }

    override fun getAddresses(): Flow<List<AddressEntity>> {
        return addressDao.getAddresses()
    }

    override fun getAddress(): Flow<AddressEntity> {
        return addressDao.getAddress()
    }

    override suspend fun updateAddress(addressEntity: AddressEntity) {
        Timber.i("updateAddress: $addressEntity")
        addressDao.updateAddress(addressEntity)
    }

    override suspend fun removeAddress(identifier: Int?) {
        Timber.i("removeAddress: $identifier")
        addressDao.removeAddress(identifier)
    }
}