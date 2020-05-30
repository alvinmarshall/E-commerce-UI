package com.cheise_proj.e_commerce.data.repository

import com.cheise_proj.e_commerce.data.db.entity.OrderEntity
import com.cheise_proj.e_commerce.data.db.entity.Orders
import kotlinx.coroutines.flow.Flow

interface IOrderRepository {
    suspend fun addOrder(orderEntity: OrderEntity)

    suspend fun getOrders(): Flow<List<Orders>>

    suspend fun removeOrder(identifier: Int?)

    suspend fun updateOrder(orderEntity: OrderEntity)
}