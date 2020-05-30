package com.cheise_proj.e_commerce.data.repository

import com.cheise_proj.e_commerce.data.db.dao.OrderDao
import com.cheise_proj.e_commerce.utils.FakeOrderService
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.test.runBlockingTest
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.Mockito.times
import org.mockito.MockitoAnnotations

@ExperimentalCoroutinesApi
@RunWith(JUnit4::class)
class OrderRepositoryTest {
    private lateinit var orderRepository: OrderRepository

    @Mock
    lateinit var orderDao: OrderDao

    @Before
    fun setUp() {
        MockitoAnnotations.initMocks(this)
        orderRepository = OrderRepository(orderDao)
    }

    @Test
    fun `Add new order success`() = runBlockingTest {
        val actual = FakeOrderService.getOrder()
        orderRepository.addOrder(actual)
        Mockito.verify(orderDao, times(1)).addItem(actual)
    }

    @Test
    fun `Update order success`() = runBlockingTest {
        val actual = FakeOrderService.getOrder()
        orderRepository.updateOrder(actual)
        Mockito.verify(orderDao, times(1)).updateCheckout(actual)
    }

    @Test
    fun `Remove order with identifier success`() = runBlockingTest {
        val actual = FakeOrderService.getOrder()
        orderRepository.removeOrder(actual.id)
        Mockito.verify(orderDao, times(1)).removeCheckout(actual.id)
    }

    @Test
    fun `Get orders info success`() = runBlockingTest {
        val actual = FakeOrderService.getOrders()
        Mockito.`when`(orderDao.getCheckoutInfo()).thenReturn(flowOf(actual))
        orderRepository.getOrders()
        Mockito.verify(orderDao, times(1)).getCheckoutInfo()
    }
}