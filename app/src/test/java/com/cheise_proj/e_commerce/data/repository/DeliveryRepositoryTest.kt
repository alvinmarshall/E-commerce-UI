package com.cheise_proj.e_commerce.data.repository

import com.cheise_proj.e_commerce.data.db.dao.DeliveryDao
import com.cheise_proj.e_commerce.utils.FakeDeliveryService
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
class DeliveryRepositoryTest {
    private lateinit var deliveryRepository: DeliveryRepository

    @Mock
    lateinit var deliveryDao: DeliveryDao

    @Before
    fun setUp() {
        MockitoAnnotations.initMocks(this)
        deliveryRepository = DeliveryRepository(deliveryDao)
    }

    @Test
    fun `Get all delivery services success`() = runBlockingTest {
        val actual = FakeDeliveryService.getDeliveryTypes()
        Mockito.`when`(deliveryDao.getDeliveries()).thenReturn(flowOf(actual))
        deliveryRepository.getDeliveries()
        Mockito.verify(deliveryDao, times(1)).getDeliveries()
    }

    @Test
    fun `Get delivery with identifier service success`() = runBlockingTest {
        val actual = FakeDeliveryService.getDeliveryTypes()[0]
        Mockito.`when`(deliveryDao.getDelivery(actual.id)).thenReturn(flowOf(actual))
        deliveryRepository.getDelivery(actual.id)
        Mockito.verify(deliveryDao, times(1)).getDelivery(actual.id)
    }
}