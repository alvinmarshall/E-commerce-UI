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
    private val _loadingState: MutableLiveData<Boolean> = MutableLiveData(true)
    private val _errorMsg: MutableLiveData<String> = MutableLiveData()
    val isLoading: LiveData<Boolean> = _loadingState
    val errorMsg: LiveData<String> = _errorMsg


    fun getProducts() {
        viewModelScope.launch {
            productRepository
                .getProducts()
                .onSuccess { products ->
                    Timber.i("product: $products")
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


}