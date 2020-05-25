package com.cheise_proj.e_commerce.ui.review

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations
import androidx.lifecycle.viewModelScope
import com.cheise_proj.e_commerce.data.repository.ProductRepository
import com.cheise_proj.e_commerce.di.IODispatcher
import com.cheise_proj.e_commerce.extension.onError
import com.cheise_proj.e_commerce.extension.onSuccess
import com.cheise_proj.e_commerce.model.Reviews
import com.cheise_proj.e_commerce.ui.BaseViewModel
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.launch
import timber.log.Timber
import javax.inject.Inject

class ReviewViewModel @Inject constructor(
    private val productRepository: ProductRepository,
    @IODispatcher private val dispatcher: CoroutineDispatcher
) : BaseViewModel() {
    private val _reviews: MutableLiveData<List<Reviews>> = MutableLiveData()
    var reviews: LiveData<List<Reviews>> = _reviews


    fun getAllReviews() {
        viewModelScope.launch(dispatcher) {
            productRepository.getReviews().onSuccess { review ->
                _reviews.postValue(review)
                _loadingState.postValue(false)
            }
                .onError { error -> Timber.i("error: $error")
                    _loadingState.postValue(false)
                    _errorMsg.postValue(error.message)
                }
        }
    }


    fun getReviews(identifier: String): LiveData<List<Reviews>> {
        reviews = Transformations.switchMap(reviews) {
            val filtered = MutableLiveData<List<Reviews>>()
            val list = it.filter { r -> r.productId == identifier }
            Timber.i("find id: $identifier: $list")
            filtered.value = list
            filtered
        }
        return reviews
    }



}