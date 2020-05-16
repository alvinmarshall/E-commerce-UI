package com.cheise_proj.e_commerce.ui.main

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.cheise_proj.e_commerce.data.repository.ProductRepository
import com.cheise_proj.e_commerce.extension.onError
import com.cheise_proj.e_commerce.extension.onSuccess
import kotlinx.coroutines.launch
import timber.log.Timber
import javax.inject.Inject

class MainViewModel @Inject constructor(private val productRepository: ProductRepository) :
    ViewModel() {

    fun getProducts() {
        viewModelScope.launch {
            productRepository
                .getProducts()
                .onSuccess { products ->
                    Timber.i("result: $products")
                }.onError { error -> Timber.i("error: $error") }
        }
    }

}