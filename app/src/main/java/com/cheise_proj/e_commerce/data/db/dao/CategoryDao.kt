package com.cheise_proj.e_commerce.data.db.dao

import androidx.room.*
import com.cheise_proj.e_commerce.data.db.entity.CategoryEntity

@Dao
interface CategoryDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun addCategory(categoryEntityList: List<CategoryEntity>)

    @Query("SELECT * FROM category")
    suspend fun getCategories(): List<CategoryEntity>

    @Query("SELECT * FROM category WHERE categoryID = :identifier")
    suspend fun getCategory(identifier: String): CategoryEntity


    @Query("DELETE FROM category")
    suspend fun deleteCategory()

    @Transaction
    suspend fun deleteAndAddCategory(categoryEntityList: List<CategoryEntity>) {
        deleteCategory()
        addCategory(categoryEntityList)
    }
}