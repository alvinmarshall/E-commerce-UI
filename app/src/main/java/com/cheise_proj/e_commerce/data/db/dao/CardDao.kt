package com.cheise_proj.e_commerce.data.db.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import com.cheise_proj.e_commerce.data.db.entity.CardEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface CardDao {
    @Insert
    fun addCard(cardEntity: CardEntity)

    @Query("SELECT * FROM card")
    fun getCards(): Flow<List<CardEntity>>

    @Query("SELECT * FROM card WHERE isDefault = :default")
    fun getCard(default: Int = 1): Flow<CardEntity>

    @Update
    fun updateCard(cardEntity: CardEntity)
}