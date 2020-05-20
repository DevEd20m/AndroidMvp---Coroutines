package com.deved.coroutinesmvp.rest

import com.deved.coroutinesmvp.data.models.detail.ResponsePlaces
import com.deved.coroutinesmvp.models.RequestLogin
import com.deved.coroutinesmvp.models.ResponseLogin
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST

interface EndPoints {

    @POST(value = "user")
    suspend fun logIn(@Body requestLogin: RequestLogin):Response<ResponseLogin>

    @GET(value = "places")
    suspend fun fetchPlaces():Response<ResponsePlaces>
}