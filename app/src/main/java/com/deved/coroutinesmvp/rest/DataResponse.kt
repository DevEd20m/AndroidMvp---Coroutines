package com.deved.coroutinesmvp.rest

sealed class DataResponse<out T: Any> {
    data class Success<T: Any>(val data : T): DataResponse<T>()
    object NetworkError :  DataResponse<Nothing>()
    data class TimeOutError(val error:String) : DataResponse<Nothing>()
    data class ServerError(val errorCode : Exception) :  DataResponse<Nothing>()
}