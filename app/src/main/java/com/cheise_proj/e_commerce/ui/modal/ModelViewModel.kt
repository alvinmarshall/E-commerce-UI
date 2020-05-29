package com.cheise_proj.e_commerce.ui.modal

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.cheise_proj.e_commerce.data.service.PromoService
import com.cheise_proj.e_commerce.model.PromoCode

class ModelViewModel : ViewModel() {

    private val _imageList: MutableLiveData<List<String>> = MutableLiveData()
    private val _promoList: MutableLiveData<List<PromoCode>> = MutableLiveData()
    val imageList: LiveData<List<String>> = _imageList
    val promoList: LiveData<List<PromoCode>> = _promoList

    fun addImage(imageList: List<String>) {
        _imageList.value = imageList
    }

    fun getPromoCodes() {
        _promoList.postValue(PromoService.getPromos())
    }

}