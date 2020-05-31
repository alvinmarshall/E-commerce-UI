package com.cheise_proj.e_commerce.ui.bag

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.cheise_proj.e_commerce.data.db.entity.CartEntity
import com.cheise_proj.e_commerce.data.db.entity.FavoriteEntity
import com.cheise_proj.e_commerce.data.db.entity.ProductWithCart
import com.cheise_proj.e_commerce.data.repository.ICartRepository
import com.cheise_proj.e_commerce.data.repository.IFavoriteRepository
import com.cheise_proj.e_commerce.di.IODispatcher
import com.cheise_proj.e_commerce.ui.BaseViewModel
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import timber.log.Timber
import javax.inject.Inject

class BagViewModel @Inject constructor(
    private val cartRepository: ICartRepository,
    private val favoriteRepository: IFavoriteRepository,
    @IODispatcher private val dispatcher: CoroutineDispatcher
) :
    BaseViewModel() {
    private val _productCart: MutableLiveData<List<ProductWithCart>> = MutableLiveData()
    val productCart: LiveData<List<ProductWithCart>> = _productCart

    fun addToCart() {
        viewModelScope.launch(dispatcher) {
            val data = CartEntity()
            data.quantity = 1
            data.productId = "1"
            cartRepository.addToCart(data)
        }

    }

    fun getProductCart() {
        viewModelScope.launch(dispatcher) {
            cartRepository.getProductCart().collect { carts ->
                Timber.i("carts: $carts")
                _productCart.postValue(carts)
                _loadingState.postValue(false)
            }
        }
    }

    fun addToFavorite(favoriteEntity: FavoriteEntity) {
        viewModelScope.launch(dispatcher) {
            favoriteRepository.addFavorite(favoriteEntity)
        }
    }

    fun removeFromCart(identifier: Int) {
        viewModelScope.launch(dispatcher) {
            cartRepository.removeCart(identifier)
        }
    }

    fun updateCart(cartEntity: CartEntity){
        viewModelScope.launch(dispatcher) {
            cartRepository.updateCart(cartEntity)
        }
    }


}