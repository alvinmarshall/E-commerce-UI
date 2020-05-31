package com.cheise_proj.e_commerce.ui.profile

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.cheise_proj.e_commerce.data.db.entity.AddressEntity
import com.cheise_proj.e_commerce.data.db.entity.CardEntity
import com.cheise_proj.e_commerce.data.db.entity.Orders
import com.cheise_proj.e_commerce.data.repository.IAddressRepository
import com.cheise_proj.e_commerce.data.repository.ICardRepository
import com.cheise_proj.e_commerce.data.repository.IOrderRepository
import com.cheise_proj.e_commerce.data.service.ProfileSectionService
import com.cheise_proj.e_commerce.di.IODispatcher
import com.cheise_proj.e_commerce.model.ProfileSection
import com.cheise_proj.e_commerce.ui.BaseViewModel
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import timber.log.Timber
import javax.inject.Inject

class ProfileViewModel @Inject constructor(
    private val addressRepository: IAddressRepository,
    private val orderRepository: IOrderRepository,
    private val cardRepository: ICardRepository,
    @IODispatcher private val dispatcher: CoroutineDispatcher
) :
    BaseViewModel() {

    private val _profileTitles: MutableLiveData<List<ProfileSection>> = MutableLiveData()
    private var _shippingAddress: MutableLiveData<List<AddressEntity>> = MutableLiveData()
    private val _shippingAddressCount:MutableLiveData<Int> = MutableLiveData()
    private val _orders:MutableLiveData<List<Orders>> = MutableLiveData()
    private val _ordersCount:MutableLiveData<Int> = MutableLiveData()
    private val _paymentMethod:MutableLiveData<CardEntity> = MutableLiveData()
    val profileTitles: LiveData<List<ProfileSection>> = _profileTitles
    val shippingAddress: LiveData<List<AddressEntity>> = _shippingAddress
    val shippingAddressCount:LiveData<Int> = _shippingAddressCount
    val orders:LiveData<List<Orders>> = _orders
    val ordersCount:LiveData<Int> = _ordersCount
    val paymentMethod:LiveData<CardEntity> = _paymentMethod

    fun getProfileTitles() {
        _profileTitles.value = ProfileSectionService.getProfileTitles()
    }

    fun getShoppingAddress() {
        viewModelScope.launch(dispatcher) {
            val data = addressRepository.getAddresses()
            data.collect {address->
                Timber.i("address: $address")
                _shippingAddressCount.postValue(address.size)
                _shippingAddress.postValue(address)
                _loadingState.postValue(false)
            }
        }
    }

    fun getOrders(){
        viewModelScope.launch(dispatcher) {
            val data = orderRepository.getOrders()
            data.collect { order->
                Timber.i("orders: $order")
                _ordersCount.postValue(order.size)
                _orders.postValue(order)
                _loadingState.postValue(false)
            }
        }
    }

    fun getDefaultPayment(){
        viewModelScope.launch(dispatcher) {
            val data = cardRepository.getCard()
            data.collect{card->
                Timber.i("card: $card")
                _paymentMethod.postValue(card)
                _loadingState.postValue(false)
            }
        }
    }






}