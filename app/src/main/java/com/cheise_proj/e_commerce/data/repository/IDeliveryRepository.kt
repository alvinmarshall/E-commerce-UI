package com.cheise_proj.e_commerce.data.repository

import com.cheise_proj.e_commerce.data.db.entity.DeliveryEntity
import kotlinx.coroutines.flow.Flow

interface IDeliveryRepository {
    suspend fun getDeliveries(): Flow<List<DeliveryEntity>>

    suspend fun getDelivery(identifier: Int?): Flow<DeliveryEntity>
}