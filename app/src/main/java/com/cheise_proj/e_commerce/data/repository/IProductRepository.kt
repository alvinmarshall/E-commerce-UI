package com.cheise_proj.e_commerce.data.repository

import com.cheise_proj.e_commerce.model.Product
import com.cheise_proj.e_commerce.utils.Result

interface IProductRepository {
    suspend fun getProducts(): Result<List<Product>>
}