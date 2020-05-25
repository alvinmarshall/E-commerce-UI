package com.cheise_proj.e_commerce.data.db.entity

import androidx.room.ColumnInfo
import androidx.room.Embedded
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "reviews")
data class ReviewEntity(
    @PrimaryKey(autoGenerate = true)
    val id: Int,
    val type: String,
    val author: String,
    val productId: String,
    val avatarUrl: String,
    val datePublished: String,
    val description: String,
    val name: String,
    val images: List<String>,
    @Embedded
    val reviewRating: ReviewRatingEntity
)

data class ReviewRatingEntity(
    @ColumnInfo(name = "review_type")
    val type: String,
    val bestRating: Int,
    val ratingValue: Int,
    val worstRating: Int
)