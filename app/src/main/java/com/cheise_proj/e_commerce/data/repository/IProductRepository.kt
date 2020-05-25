package com.cheise_proj.e_commerce.data.repository

import com.cheise_proj.e_commerce.model.Category
import com.cheise_proj.e_commerce.model.Product
import com.cheise_proj.e_commerce.model.Reviews
import com.cheise_proj.e_commerce.utils.Result

interface IProductRepository {
    suspend fun getProducts(): Result<List<Product>>
    suspend fun getProductCategories(): Result<List<Category>>
    suspend fun getCategories(): Result<List<Category>>
    suspend fun getProduct(identifier: String?): Result<Product>
    suspend fun getReviews():Result<List<Reviews>>
    suspend fun getReviewsById(identifier: String?):Result<List<Reviews>>
}