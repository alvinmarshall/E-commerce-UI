package com.cheise_proj.e_commerce.data.repository

import com.cheise_proj.e_commerce.data.db.dao.CardDao
import com.cheise_proj.e_commerce.data.db.entity.CardEntity
import kotlinx.coroutines.flow.Flow
import timber.log.Timber
import javax.inject.Inject

class CardRepository @Inject constructor(private val cardDao: CardDao) : ICardRepository {
    override suspend fun addCard(cardEntity: CardEntity) {
        Timber.i("addCard: $cardEntity")
        cardDao.addCard(cardEntity)
    }

    override fun getCards(): Flow<List<CardEntity>> {
        return cardDao.getCards()
    }

    override fun getCard(): Flow<CardEntity> {
        return cardDao.getCard()
    }

    override suspend fun updateCard(cardEntity: CardEntity) {
        Timber.i("updateCard: $cardEntity")
        cardDao.updateCard(cardEntity)
    }
}