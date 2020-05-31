package com.cheise_proj.e_commerce.ui.category

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.cheise_proj.e_commerce.data.db.entity.FavoriteEntity
import com.cheise_proj.e_commerce.data.repository.IFavoriteRepository
import com.cheise_proj.e_commerce.data.repository.IProductRepository
import com.cheise_proj.e_commerce.di.IODispatcher
import com.cheise_proj.e_commerce.extension.onError
import com.cheise_proj.e_commerce.extension.onSuccess
import com.cheise_proj.e_commerce.model.Category
import com.cheise_proj.e_commerce.model.Product
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.launch
import timber.log.Timber
import javax.inject.Inject

class CategoryViewModel @Inject constructor(
    private val productRepository: IProductRepository,
    private val favoriteRepository: IFavoriteRepository,
    @IODispatcher private val dispatcher: CoroutineDispatcher
) :
    ViewModel() {

    private val _categories: MutableLiveData<List<Category>> = MutableLiveData()
    private val _products: MutableLiveData<List<Product>> = MutableLiveData()
    private val _viewStatus: MutableLiveData<Boolean> = MutableLiveData(true)
    private val _loadingState: MutableLiveData<Boolean> = MutableLiveData(true)
    val getViewStatus: LiveData<Boolean> = _viewStatus
    val categories: LiveData<List<Category>> = _categories
    val products: LiveData<List<Product>> = _products
    val isLoading: LiveData<Boolean> = _loadingState
    private val _errorMsg: MutableLiveData<String> = MutableLiveData()
    val errorMsg: LiveData<String> = _errorMsg

    fun loadCategories() {
        viewModelScope.launch(dispatcher) {
            productRepository.getCategories()
                .onSuccess { category ->
                    _categories.postValue(category)
                    _loadingState.postValue(false)
                }
                .onError { error ->
                    Timber.i("error: $error")
                    _loadingState.postValue(false)
                    _errorMsg.postValue(error.message)
                }
        }
    }

    fun loadProducts() {
        viewModelScope.launch(dispatcher) {
            productRepository.getProducts()
                .onSuccess { product ->
                    _products.postValue(product)
                    _loadingState.postValue(false)
                }
                .onError { error ->
                    Timber.i("error: $error")
                    _loadingState.postValue(false)
                    _errorMsg.postValue(error.message)
                }
        }
    }

    fun setViewStatus(status: Boolean) {
        _viewStatus.value = status
    }
    fun addToFavorite(identifier: String) {
        viewModelScope.launch(dispatcher) {
            val favoriteEntity = FavoriteEntity()
            favoriteEntity.productId = identifier
            favoriteRepository.addFavorite(favoriteEntity)
        }
    }

}
