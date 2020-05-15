package com.cheise_proj.e_commerce.utils

import android.content.Context
import com.cheise_proj.e_commerce.extension.networkInfo
import javax.inject.Inject

class NetworkState @Inject constructor(private val context: Context) : INetworkState {
    override fun isConnected(): Boolean = context.networkInfo()
}