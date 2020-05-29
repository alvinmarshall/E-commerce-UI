package com.cheise_proj.e_commerce.data.repository

import com.cheise_proj.e_commerce.data.db.dao.CategoryDao
import com.cheise_proj.e_commerce.data.db.dao.ProductDao
import com.cheise_proj.e_commerce.data.db.dao.ReviewDao
import com.cheise_proj.e_commerce.data.service.ApiService
import com.cheise_proj.e_commerce.extension.onSuccess
import com.cheise_proj.e_commerce.extension.toEntityList
import com.cheise_proj.e_commerce.utils.FakeProductService
import com.cheise_proj.e_commerce.utils.FakeReviewService
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runBlockingTest
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4
import org.mockito.ArgumentMatchers
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.MockitoAnnotations

@ExperimentalCoroutinesApi
@RunWith(JUnit4::class)
class ProductRepositoryTest {
    private lateinit var productRepository: ProductRepository

    @Mock
    lateinit var apiService: ApiService

    @Mock
    lateinit var productDao: ProductDao

    @Mock
    lateinit var categoryDao: CategoryDao

    @Mock
    lateinit var reviewDao: ReviewDao

    @Before
    fun setUp() {
        MockitoAnnotations.initMocks(this)
        productRepository = ProductRepository(apiService, productDao, categoryDao, reviewDao)
    }


    @Test
    fun getProducts() = runBlockingTest{
        val actual = FakeProductService.getProduct()
        Mockito.`when`(apiService.getProducts()).thenReturn(FakeProductService.getProductDto())
        Mockito.`when`(productDao.getProducts()).thenReturn(actual.toEntityList())
        productRepository.getProducts().onSuccess {  }
    }

    @Test
    fun getProductCategories()= runBlockingTest {
        val actual = FakeProductService.getCategories()
        Mockito.`when`(productDao.getProducts()).thenReturn(FakeProductService.getProduct().toEntityList())
        Mockito.`when`(categoryDao.getCategories()).thenReturn(actual.toEntityList())
        productRepository.getProductCategories().onSuccess {  }
    }

    @Test
    fun getCategories()= runBlockingTest {
        val actual = FakeProductService.getCategories()
        Mockito.`when`(categoryDao.getCategories()).thenReturn(actual.toEntityList())
        productRepository.getCategories().onSuccess {  }
    }

    @Test
    fun getProduct() = runBlockingTest{
        val actual = FakeProductService.getProduct()
        Mockito.`when`(productDao.getProducts()).thenReturn(actual.toEntityList())
        productRepository.getProduct(ArgumentMatchers.anyString()).onSuccess {  }
    }

    @Test
    fun getReviews() = runBlockingTest{
        val actual = FakeReviewService.getReviews()
        Mockito.`when`(reviewDao.getReviews()).thenReturn(arrayListOf(actual).toEntityList())
        productRepository.getReviews().onSuccess {  }
    }

    @Test
    fun getReviewsById() = runBlockingTest{
        val actual = FakeReviewService.getReviews()
        Mockito.`when`(reviewDao.getReviewsById()).thenReturn(arrayListOf(actual).toEntityList())
        productRepository.getReviewsById(ArgumentMatchers.anyString()).onSuccess {  }
    }
}