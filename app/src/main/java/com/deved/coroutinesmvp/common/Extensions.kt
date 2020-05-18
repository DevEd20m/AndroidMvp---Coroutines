package com.deved.coroutinesmvp.common

import android.content.Context
import android.net.ConnectivityManager
import android.widget.Toast
import com.deved.coroutinesmvp.CoroutinesApp

fun Context.toast(message: String) {
    Toast.makeText(this, message, Toast.LENGTH_LONG).show()
}

val Context.appContext: CoroutinesApp
    get() = applicationContext as CoroutinesApp

fun Context.isAvailableNetwork(): Boolean {
//    val connectivityManager =
//        this.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
//    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
//        val networkInfo = connectivityManager
//            .getNetworkCapabilities(connectivityManager.activeNetwork)
//        networkInfo?.let {
//            val status = when {
//                networkInfo.hasCapability(NetworkCapabilities.TRANSPORT_CELLULAR) -> true
//                networkInfo.hasCapability(NetworkCapabilities.TRANSPORT_WIFI) -> true
//                networkInfo.hasCapability(NetworkCapabilities.TRANSPORT_ETHERNET) -> true
//                else -> false
//            }
//        }
//    } else {
//        val networkInfo = connectivityManager.activeNetworkInfo
//        return networkInfo != null && networkInfo.isConnected
//    }
//    return false
    val connectivityManager =this.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
    val networkInfo = connectivityManager.activeNetworkInfo
    return networkInfo != null && networkInfo.isConnected
}