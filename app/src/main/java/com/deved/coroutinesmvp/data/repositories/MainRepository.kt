package com.deved.coroutinesmvp.data.repositories

import com.deved.coroutinesmvp.models.RequestLogin
import com.deved.coroutinesmvp.models.ResponseLogin
import com.deved.coroutinesmvp.rest.DataResponse

interface MainRepository {
    suspend fun logIn(requestLogin: RequestLogin):DataResponse<ResponseLogin>
    fun getMessageNullEmail():String
    fun getMessageNullPass():String
}