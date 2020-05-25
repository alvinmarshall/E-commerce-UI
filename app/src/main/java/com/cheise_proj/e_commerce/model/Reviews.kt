package com.cheise_proj.e_commerce.model

data class Reviews(
    val id:Int,
    val type: String,
    val author: String,
    val productId: String,
    val avatarUrl: String,
    val datePublished: String,
    val description: String,
    val name: String,
    val images: List<String>,
    val reviewRating: ReviewRating
)

data class ReviewRating(
    val type: String,
    val bestRating: Int,
    val ratingValue: Int,
    val worstRating: Int
)