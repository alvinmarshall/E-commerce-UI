package com.cheise_proj.e_commerce.data.db.dao

import androidx.room.*
import com.cheise_proj.e_commerce.data.db.entity.FavoriteEntity
import com.cheise_proj.e_commerce.data.db.entity.ProductWithFavorite
import kotlinx.coroutines.flow.Flow

@Dao
interface FavoriteDao {
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun addFavorite(favoriteEntity: FavoriteEntity)

    @Query("DELETE FROM favorite WHERE id = :identifier")
    fun removeFavorite(identifier: Int?)

    @Query("SELECT * FROM favorite")
    fun getFavorite():Flow<List<FavoriteEntity>>

    @Transaction
    @Query("SELECT * FROM product INNER JOIN favorite ON favorite.productId = product.productID")
    fun getProductFavorite(): Flow<List<ProductWithFavorite>>

}