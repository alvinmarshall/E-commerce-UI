package com.cheise_proj.e_commerce.ui.main

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.cheise_proj.e_commerce.data.repository.UserRepository
import com.cheise_proj.e_commerce.extension.onError
import com.cheise_proj.e_commerce.extension.onSuccess
import kotlinx.coroutines.launch
import timber.log.Timber
import javax.inject.Inject

class MainViewModel @Inject constructor(private val userRepository: UserRepository) : ViewModel() {


    fun registerNewAccount(email: String, password: String, displayName: String) {
        viewModelScope.launch {
            userRepository.registerUser(email, password, displayName)
                .onSuccess { user -> Timber.i("user $user") }
                .onError { error -> Timber.i("error: ${error.message}") }
        }

    }

    fun loginWithEmail(email: String, password: String) {
        viewModelScope.launch {
            userRepository.signInWithEmail(email, password)
                .onSuccess { user -> Timber.i("user: $user") }
                .onError { error -> Timber.i("error: ${error.message}") }
        }
    }
}
