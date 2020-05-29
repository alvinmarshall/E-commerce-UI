package com.cheise_proj.e_commerce.data.db.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import com.cheise_proj.e_commerce.data.db.entity.AddressEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface AddressDao {
    @Insert
    fun addAddress(addressEntity: AddressEntity)

    @Query("SELECT * FROM address")
    fun getAddresses(): Flow<List<AddressEntity>>

    @Query("SELECT * FROM address WHERE isDefault = :default")
    fun getAddress(default: Int = 1): Flow<AddressEntity>

    @Update
    fun updateAddress(addressEntity: AddressEntity)

    @Query("DELETE FROM address WHERE id = :identifier")
    fun removeAddress(identifier: Int?)

}