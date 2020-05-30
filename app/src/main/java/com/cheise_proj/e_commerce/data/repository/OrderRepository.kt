package com.cheise_proj.e_commerce.data.repository

import com.cheise_proj.e_commerce.data.db.dao.OrderDao
import com.cheise_proj.e_commerce.data.db.entity.OrderEntity
import com.cheise_proj.e_commerce.data.db.entity.Orders
import kotlinx.coroutines.flow.Flow
import timber.log.Timber
import javax.inject.Inject

class OrderRepository @Inject constructor(private val orderDao: OrderDao) :
    IOrderRepository {
    override suspend fun addOrder(orderEntity: OrderEntity) {
        Timber.i("addCheckout: $orderEntity")
        orderDao.addItem(orderEntity)
    }

    override suspend fun getOrders(): Flow<List<Orders>> {
        return orderDao.getCheckoutInfo()
    }

    override suspend fun removeOrder(identifier: Int?) {
        Timber.i("removeCheckout with identifier: $identifier")
        orderDao.removeCheckout(identifier)
    }

    override suspend fun updateOrder(orderEntity: OrderEntity) {
        Timber.i("updateCheckout: $orderEntity")
        orderDao.updateCheckout(orderEntity)
    }
}