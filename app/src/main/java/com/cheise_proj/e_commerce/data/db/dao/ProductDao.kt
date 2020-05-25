package com.cheise_proj.e_commerce.data.db.dao

import androidx.room.*
import com.cheise_proj.e_commerce.data.db.entity.CategoryWithProduct
import com.cheise_proj.e_commerce.data.db.entity.ProductEntity

@Dao
interface ProductDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun addProduct(productEntityList: List<ProductEntity>)

    @Query("SELECT * FROM product")
    suspend fun getProducts(): List<ProductEntity>

    @Query("SELECT * FROM product WHERE productID = :identifier")
    suspend fun getProduct(identifier: String? = ""): ProductEntity

    @Transaction
    @Query("SELECT * FROM product")
    suspend fun getCategoryProduct(): List<CategoryWithProduct>

    @Query("DELETE FROM category")
    suspend fun deleteProduct()

    @Transaction
    suspend fun deleteAndAddProduct(productEntityList: List<ProductEntity>) {
        deleteProduct()
        addProduct(productEntityList)
    }
}