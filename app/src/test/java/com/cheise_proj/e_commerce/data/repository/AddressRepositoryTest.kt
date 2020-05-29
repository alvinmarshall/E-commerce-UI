package com.cheise_proj.e_commerce.data.repository

import com.cheise_proj.e_commerce.data.db.dao.AddressDao
import com.cheise_proj.e_commerce.utils.FakeAddressService
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.test.runBlockingTest
import org.junit.Before

import org.junit.Assert.*
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.Mockito.times
import org.mockito.MockitoAnnotations
import timber.log.Timber

@ExperimentalCoroutinesApi
@RunWith(JUnit4::class)
class AddressRepositoryTest {

    private lateinit var addressRepository: AddressRepository
    @Mock
    lateinit var addressDao: AddressDao

    @Before
    fun setUp() {
        MockitoAnnotations.initMocks(this)
        addressRepository = AddressRepository(addressDao)
    }


    @Test
    fun `Add new address success`() = runBlockingTest {
        val actual = FakeAddressService.getAddresses()[0]
        addressRepository.addAddress(actual)
        Mockito.verify(addressDao,times(1)).addAddress(actual)
    }

    @Test
    fun `Update address success`() = runBlockingTest {
        val actual = FakeAddressService.getAddresses()[0]
        addressRepository.updateAddress(actual)
        Mockito.verify(addressDao,times(1)).updateAddress(actual)
    }

    @Test
    fun `Remove address with identifier`() = runBlockingTest{
        val actual = FakeAddressService.getAddresses()[0].id
        addressRepository.removeAddress(actual)
        Mockito.verify(addressDao,times(1)).removeAddress(actual)
    }

    @Test
    fun `Get addresses success`() = runBlockingTest{
        val actual = FakeAddressService.getAddresses()
        Mockito.`when`(addressDao.getAddresses()).thenReturn(flowOf(actual))
        addressRepository.getAddresses().collect { }
        Mockito.verify(addressDao,times(1)).getAddresses()
    }

    @Test
    fun `Get address success`() = runBlockingTest{
        val actual = FakeAddressService.getAddresses()[0]
        Mockito.`when`(addressDao.getAddress()).thenReturn(flowOf(actual))
        addressRepository.getAddress().collect { }
        Mockito.verify(addressDao,times(1)).getAddress()
    }
}