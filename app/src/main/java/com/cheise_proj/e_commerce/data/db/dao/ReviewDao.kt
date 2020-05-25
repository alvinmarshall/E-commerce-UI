package com.cheise_proj.e_commerce.data.db.dao

import androidx.room.*
import com.cheise_proj.e_commerce.data.db.entity.ReviewEntity

@Dao
interface ReviewDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun addReviews(reviewEntityList: List<ReviewEntity>)

    @Query("DELETE FROM reviews")
    suspend fun deleteReviews()

    @Query("SELECT * FROM reviews")
    suspend fun getReviews(): List<ReviewEntity>

    @Query("SELECT * FROM reviews WHERE productId= :identifier")
    suspend fun getReviewsById(identifier: String? = ""): List<ReviewEntity>

    @Transaction
    suspend fun deleteAndAddReviews(reviewEntityList: List<ReviewEntity>) {
        deleteReviews()
        addReviews(reviewEntityList)
    }
}