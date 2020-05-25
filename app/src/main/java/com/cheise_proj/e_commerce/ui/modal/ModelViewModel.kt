package com.cheise_proj.e_commerce.ui.modal

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class ModelViewModel : ViewModel() {

    private val _imageList: MutableLiveData<List<String>> = MutableLiveData()
    val imageList: LiveData<List<String>> = _imageList

    fun addImage(imageList: List<String>) {
        _imageList.value = imageList
    }

}