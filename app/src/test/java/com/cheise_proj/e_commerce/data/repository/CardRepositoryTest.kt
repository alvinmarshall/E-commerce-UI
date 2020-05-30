package com.cheise_proj.e_commerce.data.repository

import com.cheise_proj.e_commerce.data.db.dao.CardDao
import com.cheise_proj.e_commerce.utils.FakeCardService
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
class CardRepositoryTest {
    private lateinit var cardRepository: CardRepository

    @Mock
    lateinit var cardDao: CardDao

    @Before
    fun setUp() {
        MockitoAnnotations.initMocks(this)
        cardRepository = CardRepository(cardDao)
    }

    @Test
    fun `Add new card`() = runBlockingTest {
        val actual = FakeCardService.getCards()[0]
        cardRepository.addCard(actual)
        Mockito.verify(cardDao, times(1)).addCard(actual)
    }

    @Test
    fun `Update card success`() = runBlockingTest {
        val actual = FakeCardService.getCards()[0]
        actual.isDefault = 0
        cardRepository.updateCard(actual)
        Mockito.verify(cardDao, times(1)).updateCard(actual)
    }

    @Test
    fun `Get  payment cards success`() = runBlockingTest {
        val actual = FakeCardService.getCards()
        Mockito.`when`(cardDao.getCards()).thenReturn(flowOf(actual))
        cardRepository.getCards()
        Mockito.verify(cardDao, times(1)).getCards()
    }

    @Test
    fun `Get default payment card success`() = runBlockingTest {
        val actual = FakeCardService.getCards()[0]
        Mockito.`when`(cardDao.getCard()).thenReturn(flowOf(actual))
        cardRepository.getCard()
        Mockito.verify(cardDao, times(1)).getCard()
    }
}