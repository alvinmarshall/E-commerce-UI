package com.cheise_proj.e_commerce.data.repository

import com.cheise_proj.e_commerce.data.db.dao.CartDao
import com.cheise_proj.e_commerce.utils.FakeCartService
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
class CartRepositoryTest {
    private lateinit var cartRepository: CartRepository

    @Mock
    lateinit var cartDao: CartDao

    @Before
    fun setUp() {
        MockitoAnnotations.initMocks(this)
        cartRepository = CartRepository(cartDao)
    }

    @Test
    fun `Add item to cart success`() = runBlockingTest {
        val actual = FakeCartService.getCart()
        cartRepository.addToCart(actual)
        Mockito.verify(cartDao, times(1)).addToCart(actual)
    }

    @Test
    fun `Remove item from cart success`() = runBlockingTest {
        val actual = FakeCartService.getCart()
        cartRepository.removeCart(actual.id)
        Mockito.verify(cartDao, times(1)).removeCart(actual.id)
    }

    @Test
    fun `update cart success`() = runBlockingTest {
        val actual = FakeCartService.getCart()
        cartRepository.updateCart(actual)
        Mockito.verify(cartDao, times(1)).updateCart(actual)
    }

    @Test
    fun `Get available carts success`() {
        val actual = FakeCartService.getProductCart()
        Mockito.`when`(cartDao.getProductCart()).thenReturn(flowOf(actual))
        cartRepository.getProductCart()
        Mockito.verify(cartDao, times(1)).getProductCart()
    }
}