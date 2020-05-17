package com.cheise_proj.e_commerce.data.service

import com.cheise_proj.e_commerce.model.dto.CategoryDto
import com.cheise_proj.e_commerce.model.dto.ProductDto
import retrofit2.http.GET

interface ApiService {

    @GET("products.json")
    suspend fun getProducts(): ProductDto

    @GET("categories.json")
    suspend fun getCategories(): CategoryDto
}