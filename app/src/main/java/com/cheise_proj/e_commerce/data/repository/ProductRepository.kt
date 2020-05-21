package com.cheise_proj.e_commerce.data.repository

import com.cheise_proj.e_commerce.data.service.ApiService
import com.cheise_proj.e_commerce.extension.toModelList
import com.cheise_proj.e_commerce.model.Category
import com.cheise_proj.e_commerce.model.Product
import com.cheise_proj.e_commerce.utils.Error
import com.cheise_proj.e_commerce.utils.Result
import com.cheise_proj.e_commerce.utils.Success
import javax.inject.Inject

class ProductRepository @Inject constructor(private val apiService: ApiService) :
    IProductRepository {
    override suspend fun getProducts(): Result<List<Product>> {
        return try {
            val productDto = apiService.getProducts()
            Success(productDto.data.toModelList())

        } catch (e: Exception) {
            Error(e)

        }
    }

    override suspend fun getProductCategories(): Result<List<Category>> {
        return try {
            val categoryDto = apiService.getCategories()
            val productDto = apiService.getProducts()
            val category = categoryDto.data.toModelList()
            val product = productDto.data.toModelList().shuffled()
            category.map {
                it.product = product
            }
            Success(category)

        } catch (e: Exception) {
            Error(e)
        }
    }

    override suspend fun getCategories(): Result<List<Category>> {
        return try {
            val categoryDto = apiService.getCategories()
            val category = categoryDto.data.toModelList()
            Success(category)

        } catch (e: Exception) {
            Error(e)
        }
    }


}