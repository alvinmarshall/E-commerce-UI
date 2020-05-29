package com.cheise_proj.e_commerce.ui.product

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.cheise_proj.e_commerce.data.db.entity.FavoriteEntity
import com.cheise_proj.e_commerce.data.repository.FavoriteRepository
import com.cheise_proj.e_commerce.data.repository.IFavoriteRepository
import com.cheise_proj.e_commerce.data.repository.IProductRepository
import com.cheise_proj.e_commerce.di.IODispatcher
import com.cheise_proj.e_commerce.extension.onError
import com.cheise_proj.e_commerce.extension.onSuccess
import com.cheise_proj.e_commerce.model.Category
import com.cheise_proj.e_commerce.model.Product
import com.cheise_proj.e_commerce.ui.BaseViewModel
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.launch
import timber.log.Timber
import javax.inject.Inject

class ProductViewModel @Inject constructor(
    private val productRepository: IProductRepository,
    private val favoriteRepository: IFavoriteRepository,
    @IODispatcher private val dispatcher: CoroutineDispatcher
) :
    BaseViewModel() {
    private val _productCategory: MutableLiveData<List<Category>> = MutableLiveData()
    private val _product: MutableLiveData<Product> = MutableLiveData()
    private val _products: MutableLiveData<List<Product>> = MutableLiveData()

    val productCategory: LiveData<List<Category>> = _productCategory
    val product: LiveData<Product> = _product
    val products: LiveData<List<Product>> = _products


    fun getProduct(identifier: String?) {
        viewModelScope.launch(dispatcher) {
            productRepository
                .getProduct(identifier)
                .onSuccess { product ->
                    Timber.i("product: $product")
                    _product.postValue(product)
                }.onError { error ->
                    Timber.i("error: ${error.message}")
//                    _loadingState.postValue(true)
                    _errorMsg.postValue(error.message)
                }
        }
    }

    fun getMayLikeProducts() {
        viewModelScope.launch(dispatcher) {
            productRepository
                .getProducts()
                .onSuccess { products ->
                    Timber.i("product: $products")
                    val select = products.size / 2
                    val newData = products.take(select).shuffled()
                    _products.postValue(newData)
                    _loadingState.postValue(false)
                }.onError { error ->
                    Timber.i("error: ${error.message}")
                    _loadingState.postValue(false)
                    _errorMsg.postValue(error.message)
                }
        }
    }

    fun getProductCategories() {
        viewModelScope.launch(dispatcher) {
            productRepository.getProductCategories()
                .onSuccess { category ->
                    Timber.i("category: $category")
                    _productCategory.postValue(category)
                    _loadingState.postValue(false)
                }
                .onError { error ->
                    Timber.i("error: ${error.message}")
                    _loadingState.postValue(false)
                    _errorMsg.postValue(error.message)
                }
        }
    }

    fun getCategories() {
        viewModelScope.launch(dispatcher) {
            productRepository.getProductCategories()
                .onSuccess { category ->
                    Timber.i("category: $category")
                    _productCategory.postValue(category)
                    _loadingState.postValue(false)
                }
                .onError { error ->
                    Timber.i("error: ${error.message}")
                    _loadingState.postValue(false)
                    _errorMsg.postValue(error.message)
                }
        }
    }

    fun addToFavorite(identifier: String) {
        viewModelScope.launch(dispatcher) {
            val favoriteEntity = FavoriteEntity()
            favoriteEntity.productId = identifier
            favoriteRepository.addFavorite(favoriteEntity)
        }
    }


}