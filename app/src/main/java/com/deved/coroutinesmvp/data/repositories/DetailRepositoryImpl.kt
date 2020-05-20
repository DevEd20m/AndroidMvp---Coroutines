package com.deved.coroutinesmvp.data.repositories

import android.app.Application
import com.deved.coroutinesmvp.R
import com.deved.coroutinesmvp.common.isAvailableNetwork
import com.deved.coroutinesmvp.data.models.ErrorBody
import com.deved.coroutinesmvp.data.models.detail.ResponsePlaces
import com.deved.coroutinesmvp.rest.ApiClient
import com.deved.coroutinesmvp.rest.DataResponse
import com.google.gson.Gson
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import java.net.SocketTimeoutException

class DetailRepositoryImpl(private val application: Application) : DetailRepository {
    override suspend fun fetchPlaces(): DataResponse<ResponsePlaces> =
        withContext(Dispatchers.IO) {
            try {
                if (!application.isAvailableNetwork()) return@withContext DataResponse.NetworkError(
                    application.resources.getString(R.string.errorNetwork)
                )
                val result = ApiClient.endPoints.fetchPlaces()
                if (result.isSuccessful) {
                    result.body()?.let { return@withContext DataResponse.Success(it) }
                }
                return@withContext DataResponse.ServerError(
                    Gson().fromJson(
                        result.errorBody()?.charStream(),
                        ErrorBody::class.java
                    )
                )
            } catch (e: SocketTimeoutException) {
                return@withContext DataResponse.TimeOutServerError(application.resources.getString(R.string.serverNotResponding))
            } catch (e: Exception) {
                return@withContext DataResponse.ExceptionError(e)
            }
        }
}