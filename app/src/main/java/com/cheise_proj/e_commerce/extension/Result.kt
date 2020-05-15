package com.cheise_proj.e_commerce.extension

import com.cheise_proj.e_commerce.utils.Error
import com.cheise_proj.e_commerce.utils.Result
import com.cheise_proj.e_commerce.utils.Success

inline fun <T> Result<T>.onSuccess(action: (T) -> Unit): Result<T> {
    if (this is Success) action(data)
    return this
}

inline fun <T> Result<T>.onError(action: (Error) -> Unit): Result<T> {
    if (this is Error) action(this)
    return this
}