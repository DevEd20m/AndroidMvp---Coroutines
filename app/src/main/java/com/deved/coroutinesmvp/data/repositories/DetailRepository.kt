package com.deved.coroutinesmvp.data.repositories

import com.deved.coroutinesmvp.data.models.detail.ResponsePlaces
import com.deved.coroutinesmvp.rest.DataResponse

interface DetailRepository {
    suspend fun fetchPlaces():DataResponse<ResponsePlaces>
}