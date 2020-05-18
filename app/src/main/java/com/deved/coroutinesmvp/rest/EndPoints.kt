package com.deved.coroutinesmvp.rest

import com.deved.coroutinesmvp.models.RequestLogin
import com.deved.coroutinesmvp.models.ResponseLogin
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.POST

interface EndPoints {

    @POST(value = "user")
    suspend fun logIn(@Body requestLogin: RequestLogin):Response<ResponseLogin>
}