package com.cheise_proj.e_commerce.ui.category

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.cheise_proj.e_commerce.data.repository.ProductRepository
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
    private val productRepository: ProductRepository,
    @IODispatcher private val dispatcher: CoroutineDispatcher
) :
    ViewModel() {

    private val _categories: MutableLiveData<List<Category>> = MutableLiveData()
    private val _products: MutableLiveData<List<Product>> = MutableLiveData()
    private val _viewStatus: MutableLiveData<Boolean> = MutableLiveData(true)
    val getViewStatus: LiveData<Boolean> = _viewStatus
    val categories: LiveData<List<Category>> = _categories
    val products: LiveData<List<Product>> = _products

    fun loadCategories() {
        viewModelScope.launch(dispatcher) {
            productRepository.getCategories()
                .onSuccess { category -> _categories.postValue(category) }
                .onError { error -> Timber.i("error: $error") }
        }
    }

    fun loadProducts() {
        viewModelScope.launch(dispatcher) {
            productRepository.getProducts()
                .onSuccess { product -> _products.postValue(product) }
                .onError { error -> Timber.i("error: $error") }
        }
    }

    fun setViewStatus(status: Boolean) {
        _viewStatus.value = status
    }

}
