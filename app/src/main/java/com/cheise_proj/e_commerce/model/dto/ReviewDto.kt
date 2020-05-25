package com.cheise_proj.e_commerce.model.dto


import com.google.gson.annotations.SerializedName

data class ReviewDto(
    val review: List<ReviewData>
)

data class ReviewData(
    @SerializedName("@type")
    val type: String,
    val author: String,
    val productId: String,
    val avatarUrl: String,
    val datePublished: String,
    val description: String,
    val name: String,
    val images: List<String>,
    val reviewRating: ReviewRatingData
)

data class ReviewRatingData(
    @SerializedName("@type")
    val type: String,
    val bestRating: Int,
    val ratingValue: Int,
    val worstRating: Int
)