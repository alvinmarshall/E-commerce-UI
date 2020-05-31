package com.cheise_proj.e_commerce.data.db.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.cheise_proj.e_commerce.data.db.entity.DeliveryEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface DeliveryDao {

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun addDelivery(deliveryEntity: DeliveryEntity)

    @Query("SELECT * FROM delivery")
    fun getDeliveries(): Flow<List<DeliveryEntity>>

    @Query("SELECT * FROM delivery WHERE id = :identifier")
    fun getDelivery(identifier: Int?): Flow<DeliveryEntity>

}