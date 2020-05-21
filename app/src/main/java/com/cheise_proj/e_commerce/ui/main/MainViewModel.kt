package com.cheise_proj.e_commerce.ui.main

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.cheise_proj.e_commerce.data.repository.IProductRepository
import com.cheise_proj.e_commerce.di.IODispatcher
import com.cheise_proj.e_commerce.extension.onError
import com.cheise_proj.e_commerce.extension.onSuccess
import com.cheise_proj.e_commerce.model.Category
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.launch
import timber.log.Timber
import javax.inject.Inject

class MainViewModel @Inject constructor(
    private val productRepository: IProductRepository,
    @IODispatcher private val dispatcher: CoroutineDispatcher
) :
    ViewModel() {
    private val _productCategory: MutableLiveData<List<Category>> = MutableLiveData()
    val productCategory: LiveData<List<Category>> = _productCategory

    fun getProducts() {
        viewModelScope.launch {
            productRepository
                .getProducts()
                .onSuccess { products ->
                    Timber.i("product: $products")
                }.onError { error -> Timber.i("error: ${error.message}") }
        }
    }

    fun getProductCategories() {
        viewModelScope.launch {
            productRepository.getProductCategories()
                .onSuccess { category ->
                    Timber.i("category: $category")
                    _productCategory.value = category
                }
                .onError { error -> Timber.i("error: ${error.message}") }
        }
    }

    fun getCategories() {
        viewModelScope.launch {
            productRepository.getProductCategories()
                .onSuccess { category ->
                    Timber.i("category: $category")
                    _productCategory.value = category
                }
                .onError { error -> Timber.i("error: ${error.message}") }
        }
    }


}