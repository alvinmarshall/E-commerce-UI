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
    val categories: LiveData<List<Category>> = _categories

    fun loadCategories() {
        viewModelScope.launch(dispatcher) {
            productRepository.getCategories()
                .onSuccess { category -> _categories.postValue(category) }
                .onError { error -> Timber.i("error: $error") }
        }
    }

}
