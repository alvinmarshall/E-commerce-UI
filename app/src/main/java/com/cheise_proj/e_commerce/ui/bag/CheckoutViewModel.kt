package com.cheise_proj.e_commerce.ui.bag

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.cheise_proj.e_commerce.data.db.entity.AddressEntity
import com.cheise_proj.e_commerce.data.db.entity.CardEntity
import com.cheise_proj.e_commerce.data.db.entity.DeliveryEntity
import com.cheise_proj.e_commerce.data.db.entity.OrderEntity
import com.cheise_proj.e_commerce.data.repository.IAddressRepository
import com.cheise_proj.e_commerce.data.repository.ICardRepository
import com.cheise_proj.e_commerce.data.repository.IDeliveryRepository
import com.cheise_proj.e_commerce.data.repository.IOrderRepository
import com.cheise_proj.e_commerce.di.IODispatcher
import com.cheise_proj.e_commerce.ui.BaseViewModel
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import timber.log.Timber
import javax.inject.Inject

class CheckoutViewModel @Inject constructor(
    private val addressRepository: IAddressRepository,
    private val cardRepository: ICardRepository,
    private val orderRepository: IOrderRepository,
    private val deliveryRepository: IDeliveryRepository,
    @IODispatcher private val dispatcher: CoroutineDispatcher
) : BaseViewModel() {
    private val _defaultAddress: MutableLiveData<AddressEntity> = MutableLiveData()
    private val _addresses: MutableLiveData<List<AddressEntity>> = MutableLiveData()
    private val _cards: MutableLiveData<List<CardEntity>> = MutableLiveData()
    private val _card: MutableLiveData<CardEntity> = MutableLiveData()
    private val _deliveries: MutableLiveData<List<DeliveryEntity>> = MutableLiveData()
    private val _delivery: MutableLiveData<DeliveryEntity> = MutableLiveData()
    private val _deliveryCost: MutableLiveData<Int> = MutableLiveData(0)
    val defaultAddress: LiveData<AddressEntity> = _defaultAddress
    val addresses: LiveData<List<AddressEntity>> = _addresses
    val cards: LiveData<List<CardEntity>> = _cards
    val defaultCard: LiveData<CardEntity> = _card
    val deliveryType: LiveData<List<DeliveryEntity>> = _deliveries
    val delivery: LiveData<DeliveryEntity> = _delivery
    val deliveryCost: LiveData<Int> = _deliveryCost

    fun getDefaultAddress() {
        viewModelScope.launch(dispatcher) {
            val data = addressRepository.getAddress()
            data.collect {
                _defaultAddress.postValue(it)
                _loadingState.postValue(false)
            }
        }
    }

    fun getAddresses() {
        viewModelScope.launch(dispatcher) {
            val data = addressRepository.getAddresses()
            data.collect { addresses ->
                Timber.i("addresses: $addresses")
                _addresses.postValue(addresses)
                _loadingState.postValue(false)
            }
        }

    }

    fun addAddress(addressEntity: AddressEntity) {
        viewModelScope.launch(dispatcher) {
            addressRepository.addAddress(addressEntity)
        }
    }

    fun updateAddress(addressEntity: AddressEntity) {
        viewModelScope.launch(dispatcher) {
            addressRepository.updateAddress(addressEntity)
        }
    }

    fun getPaymentCards() {
        viewModelScope.launch(dispatcher) {
            val data = cardRepository.getCards()
            data.collect { cards ->
                _cards.postValue(cards)
                _loadingState.postValue(false)
            }
        }
    }

    fun addPaymentCard(cardEntity: CardEntity) {
        viewModelScope.launch(dispatcher) {
            cardRepository.addCard(cardEntity)
        }
    }

    fun updatePaymentCard(cardEntity: CardEntity) {
        viewModelScope.launch(dispatcher) {
            cardRepository.updateCard(cardEntity)
        }
    }

    fun getDefaultCard() {
        viewModelScope.launch(dispatcher) {
            val data = cardRepository.getCard()
            data.collect {
                _card.postValue(it)
                _loadingState.postValue(false)
            }
        }
    }

    fun getDeliveryTypes() {
        viewModelScope.launch(dispatcher) {
            val data = deliveryRepository.getDeliveries()
            data.collect { delivery ->
                Timber.i("deliveryType: $delivery")
                _deliveries.postValue(delivery)
                _loadingState.postValue(false)
            }
        }
    }

    fun getDelivery(identifier: Int?) {
        viewModelScope.launch(dispatcher) {
            val data = deliveryRepository.getDelivery(identifier)
            data.collect { delivery ->
                _deliveryCost.postValue(delivery.cost)
                _delivery.postValue(delivery)
            }
        }
    }

    fun addOrder(orderEntity: OrderEntity) {
        viewModelScope.launch(dispatcher) {
            orderRepository.addOrder(orderEntity)
        }
    }


}