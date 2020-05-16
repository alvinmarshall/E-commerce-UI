package com.cheise_proj.e_commerce.data.repository

import com.cheise_proj.e_commerce.data.service.ApiService
import com.cheise_proj.e_commerce.extension.toModelList
import com.cheise_proj.e_commerce.model.Product
import com.cheise_proj.e_commerce.utils.Error
import com.cheise_proj.e_commerce.utils.Result
import com.cheise_proj.e_commerce.utils.Success
import javax.inject.Inject

class ProductRepository @Inject constructor(private val apiService: ApiService) :
    IProductRepository {
    override suspend fun getProducts(): Result<List<Product>> {
        return try {
            var productLst: List<Product> = arrayListOf()
            apiService.getProducts().map {
                it.data.toModelList()
            }.map {
                println("products: $it")
                productLst = it
            }
            Success(productLst)

        } catch (e: Exception) {
            Error(e)

        }
    }


}