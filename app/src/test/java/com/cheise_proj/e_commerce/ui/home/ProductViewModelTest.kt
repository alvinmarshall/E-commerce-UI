package com.cheise_proj.e_commerce.ui.home

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.cheise_proj.e_commerce.data.repository.IFavoriteRepository
import com.cheise_proj.e_commerce.data.repository.IProductRepository
import com.cheise_proj.e_commerce.ui.product.ProductViewModel
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
class ProductViewModelTest {


    @get:Rule
    val instantTask = InstantTaskExecutorRule()


    @Mock
    lateinit var productRepository: IProductRepository

    @Mock
    lateinit var favoriteRepository: IFavoriteRepository
    private lateinit var productViewModel: ProductViewModel

    @ExperimentalCoroutinesApi
    private val testDispatcher = TestCoroutineDispatcher()

    @ExperimentalCoroutinesApi
    private val testScope = TestCoroutineScope(testDispatcher)

    @ExperimentalCoroutinesApi
    @Before
    fun setUp() {
        MockitoAnnotations.initMocks(this)
        Dispatchers.setMain(testDispatcher)
        productViewModel = ProductViewModel(
            productRepository, favoriteRepository,
            TestCoroutineDispatcher()
        )
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
        productViewModel.getCategories()
        val productLive = productViewModel.productCategory
        productLive.observeForever { }
        assertTrue(
            productLive.value.isNullOrEmpty()
        )
    }
}