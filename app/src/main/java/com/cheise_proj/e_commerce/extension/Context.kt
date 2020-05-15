package com.cheise_proj.e_commerce.extension

import android.content.Context
import android.net.ConnectivityManager
import android.net.Network
import android.net.NetworkCapabilities

fun Context.networkInfo(): Boolean {
    val connectivityManager =
        (this.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager)

    val network: Network = connectivityManager.activeNetwork ?: return false

    val activeNetwork: NetworkCapabilities =
        connectivityManager.getNetworkCapabilities(network) ?: return false

    return when {
        activeNetwork.hasTransport(NetworkCapabilities.TRANSPORT_WIFI) -> true
        activeNetwork.hasTransport(NetworkCapabilities.TRANSPORT_CELLULAR) -> true
        else -> false
    }

}