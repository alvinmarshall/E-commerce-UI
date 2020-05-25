package com.cheise_proj.e_commerce.data.service

import com.cheise_proj.e_commerce.model.dto.CategoryDto
import com.cheise_proj.e_commerce.model.dto.ProductDto
import com.cheise_proj.e_commerce.model.dto.ReviewDto
import retrofit2.http.GET

interface ApiService {

    @GET("products.json")
    suspend fun getProducts(): ProductDto

    @GET("categories.json")
    suspend fun getCategories(): CategoryDto

    @GET("reviews.json")
    suspend fun getReviews(): ReviewDto
}