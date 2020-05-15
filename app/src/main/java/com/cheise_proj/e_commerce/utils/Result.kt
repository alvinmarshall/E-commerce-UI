package com.cheise_proj.e_commerce.utils

sealed class Result<out T>
class Success<out T>(val data: T) : Result<T>()
class Error(private val exception: Throwable, val message: String? = exception.localizedMessage) :
    Result<Nothing>()