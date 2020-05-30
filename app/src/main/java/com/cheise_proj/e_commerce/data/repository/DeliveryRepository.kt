package com.cheise_proj.e_commerce.data.repository

import com.cheise_proj.e_commerce.data.db.dao.DeliveryDao
import com.cheise_proj.e_commerce.data.db.entity.DeliveryEntity
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class DeliveryRepository @Inject constructor(private val deliveryDao: DeliveryDao) :
    IDeliveryRepository {
    override suspend fun getDeliveries(): Flow<List<DeliveryEntity>> {
        return deliveryDao.getDeliveries()
    }

    override suspend fun getDelivery(identifier: Int?): Flow<DeliveryEntity> {
        return deliveryDao.getDelivery(identifier)
    }
}