package com.cheise_proj.e_commerce.data.repository

import com.cheise_proj.e_commerce.data.db.dao.CategoryDao
import com.cheise_proj.e_commerce.data.db.dao.ProductDao
import com.cheise_proj.e_commerce.data.db.dao.ReviewDao
import com.cheise_proj.e_commerce.data.service.ApiService
import com.cheise_proj.e_commerce.extension.*
import com.cheise_proj.e_commerce.model.Category
import com.cheise_proj.e_commerce.model.Product
import com.cheise_proj.e_commerce.model.Reviews
import com.cheise_proj.e_commerce.utils.Error
import com.cheise_proj.e_commerce.utils.Result
import com.cheise_proj.e_commerce.utils.Success
import javax.inject.Inject

class ProductRepository @Inject constructor(
    private val apiService: ApiService,
    private val productDao: ProductDao,
    private val categoryDao: CategoryDao,
    private val reviewDao: ReviewDao
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
                productDao.deleteAndAddProduct(product.toEntityList())
                categoryDao.deleteAndAddCategory(category.toEntityList())

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

    override suspend fun getProduct(identifier: String?): Result<Product> {
        return try {
            val local = productDao.getProduct(identifier)
            Success(local.toProduct())
        } catch (e: Exception) {
            Error(e)
        }
    }

    override suspend fun getReviews(): Result<List<Reviews>> {
        return try {
            val local = reviewDao.getReviews()
            if (local.isEmpty()) {
                val reviewDto = apiService.getReviews()
                val data = reviewDto.review.toModelList()
                reviewDao.deleteAndAddReviews(data.toEntityList())
                return Success(data)
            }
            Success(local.toReviewList())
        } catch (e: Exception) {
            Error(e)
        }
    }

    override suspend fun getReviewsById(identifier: String?): Result<List<Reviews>> {
        return try {
            val local = reviewDao.getReviewsById(identifier)
            Success(local.toReviewList())
        } catch (e: Exception) {
            Error(e)
        }
    }


}