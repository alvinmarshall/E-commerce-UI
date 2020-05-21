package com.cheise_proj.e_commerce.ui.main

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.cheise_proj.e_commerce.data.repository.IProductRepository
import com.cheise_proj.e_commerce.utils.FakeProductService
import com.cheise_proj.e_commerce.utils.Success
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.*
import org.junit.After
import org.junit.Assert.assertTrue
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.MockitoAnnotations

@RunWith(JUnit4::class)
class MainViewModelTest {


    @get:Rule
    val instantTask = InstantTaskExecutorRule()


    @Mock
    lateinit var productRepository: IProductRepository
    private lateinit var mainViewModel: MainViewModel

    @ExperimentalCoroutinesApi
    private val testDispatcher = TestCoroutineDispatcher()

    @ExperimentalCoroutinesApi
    private val testScope = TestCoroutineScope(testDispatcher)

    @ExperimentalCoroutinesApi
    @Before
    fun setUp() {
        MockitoAnnotations.initMocks(this)
        Dispatchers.setMain(testDispatcher)
        mainViewModel = MainViewModel(productRepository, TestCoroutineDispatcher())
    }

    @ExperimentalCoroutinesApi
    @After
    fun after() {
        Dispatchers.resetMain()
        testScope.cleanupTestCoroutines()
    }

    @ExperimentalCoroutinesApi
    @Test
    fun `Get Product Categories success`() = testScope.runBlockingTest {
        val actual = FakeProductService.getCategories()
        Mockito.`when`(productRepository.getCategories()).thenReturn(Success(actual))
        mainViewModel.getCategories()
        val productLive = mainViewModel.productCategory
        productLive.observeForever { }
        assertTrue(
            productLive.value.isNullOrEmpty()
        )
    }
}