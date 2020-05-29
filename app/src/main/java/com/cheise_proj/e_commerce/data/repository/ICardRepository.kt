package com.cheise_proj.e_commerce.data.repository

import com.cheise_proj.e_commerce.data.db.entity.CardEntity
import kotlinx.coroutines.flow.Flow

interface ICardRepository {
    suspend fun addCard(cardEntity: CardEntity)

    fun getCards(): Flow<List<CardEntity>>

    fun getCard(): Flow<CardEntity>

    suspend fun updateCard(cardEntity: CardEntity)
}
