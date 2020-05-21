package com.cheise_proj.e_commerce.data.repository

import com.cheise_proj.e_commerce.data.db.dao.CategoryDao
import com.cheise_proj.e_commerce.data.db.dao.ProductDao
import com.cheise_proj.e_commerce.data.service.ApiService
import com.cheise_proj.e_commerce.extension.toCategoryList
import com.cheise_proj.e_commerce.extension.toEntityList
import com.cheise_proj.e_commerce.extension.toModelList
import com.cheise_proj.e_commerce.extension.toProductList
import com.cheise_proj.e_commerce.model.Category
import com.cheise_proj.e_commerce.model.Product
import com.cheise_proj.e_commerce.utils.Error
import com.cheise_proj.e_commerce.utils.Result
import com.cheise_proj.e_commerce.utils.Success
import javax.inject.Inject

class ProductRepository @Inject constructor(
    private val apiService: ApiService,
    private val productDao: ProductDao,
    private val categoryDao: CategoryDao
) :
    IProductRepository {
    override suspend fun getProducts(): Result<List<Product>> {
        return try {
            val local = productDao.getProducts()
            if (local.isEmpty()) {
                val productDto = apiService.getProducts()
                val data = productDto.data.toModelList()
                productDao.deleteAndAddProduct(data.toEntityList())
                return Success(data)
            }

            Success(local.toProductList())

        } catch (e: Exception) {
            Error(e)

        }
    }

    override suspend fun getProductCategories(): Result<List<Category>> {
        return try {
            val productLocal = productDao.getProducts()
            val categoryLocal = categoryDao.getCategories()
            if (productLocal.isEmpty() && categoryLocal.isEmpty()) {
                val categoryDto = apiService.getCategories()
                val productDto = apiService.getProducts()
                val category = categoryDto.data.toModelList()
                val product = productDto.data.toModelList().shuffled()
                category.map {
                    it.product = product
                }
                return Success(category)
            }
            categoryLocal.map {
                it.product = productLocal.toProductList()
            }

            Success(categoryLocal.toCategoryList())


        } catch (e: Exception) {
            Error(e)
        }
    }

    override suspend fun getCategories(): Result<List<Category>> {
        return try {
            val local = categoryDao.getCategories()
            if (local.isEmpty()) {
                val categoryDto = apiService.getCategories()
                val data = categoryDto.data.toModelList()
                categoryDao.addCategory(data.toEntityList())
                return Success(data)
            }
            Success(local.toCategoryList())


        } catch (e: Exception) {
            Error(e)
        }
    }


}