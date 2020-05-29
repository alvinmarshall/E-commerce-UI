package com.cheise_proj.e_commerce.ui.bag

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.cheise_proj.e_commerce.data.db.entity.AddressEntity
import com.cheise_proj.e_commerce.data.db.entity.CardEntity
import com.cheise_proj.e_commerce.data.repository.CardRepository
import com.cheise_proj.e_commerce.data.repository.IAddressRepository
import com.cheise_proj.e_commerce.data.repository.ICardRepository
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
    @IODispatcher private val dispatcher: CoroutineDispatcher
) : BaseViewModel() {
    private val _defaultAddress: MutableLiveData<AddressEntity> = MutableLiveData()
    private val _addresses: MutableLiveData<List<AddressEntity>> = MutableLiveData()
    private val _cards: MutableLiveData<List<CardEntity>> = MutableLiveData()
    private val _card: MutableLiveData<CardEntity> = MutableLiveData()
    val defaultAddress: LiveData<AddressEntity> = _defaultAddress
    val addresses: LiveData<List<AddressEntity>> = _addresses
    val cards: LiveData<List<CardEntity>> = _cards
    val defaultCard: LiveData<CardEntity> = _card

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


}