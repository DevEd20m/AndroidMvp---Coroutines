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
    val connectivityManager = this.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
    val networkInfo = connectivityManager.activeNetworkInfo
    return networkInfo != null && networkInfo.isConnected
}