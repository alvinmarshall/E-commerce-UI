package com.cheise_proj.e_commerce.data.repository

import com.cheise_proj.e_commerce.data.db.dao.FavoriteDao
import com.cheise_proj.e_commerce.utils.FakeFavoriteService
import kotlinx.coroutines.ExperimentalCoroutinesApi
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

@ExperimentalCoroutinesApi
@RunWith(JUnit4::class)
class FavoriteRepositoryTest {
    private lateinit var favoriteRepository: FavoriteRepository

    @Mock
    lateinit var favoriteDao: FavoriteDao

    @Before
    fun setUp() {
        MockitoAnnotations.initMocks(this)
        favoriteRepository = FavoriteRepository(favoriteDao)
    }


    @Test
    fun `Add item to favorite success`() = runBlockingTest {
        val actual = FakeFavoriteService.getFavorite()
        favoriteRepository.addFavorite(actual)
        Mockito.verify(favoriteDao, times(1)).addFavorite(actual)
    }

    @Test
    fun `Remove item with identifier from favorite success`() = runBlockingTest {
        val actual = FakeFavoriteService.getFavorite()
        favoriteRepository.removeFavorite(actual.id)
        Mockito.verify(favoriteDao, times(1)).removeFavorite(actual.id)
    }

    @Test
    fun `Get favorite items success`() = runBlockingTest {
        val actual = arrayListOf(FakeFavoriteService.getFavorite())
        Mockito.`when`(favoriteDao.getFavorite()).thenReturn(flowOf(actual))
        favoriteRepository.getFavorites()
        Mockito.verify(favoriteDao, times(1)).getFavorite()
    }

    @Test
    fun `Get favorite products success`() = runBlockingTest {
        val actual = FakeFavoriteService.getProductWithFavorite()
        Mockito.`when`(favoriteDao.getProductFavorite()).thenReturn(flowOf(actual))
        favoriteRepository.getProductFavorite()
        Mockito.verify(favoriteDao, times(1)).getProductFavorite()
    }
}