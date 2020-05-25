package com.cheise_proj.e_commerce.ui

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

abstract class BaseViewModel : ViewModel() {
    protected val _loadingState: MutableLiveData<Boolean> = MutableLiveData(true)
    protected val _errorMsg: MutableLiveData<String> = MutableLiveData()
     val isLoading: LiveData<Boolean> = _loadingState
     val errorMsg: LiveData<String> = _errorMsg
}