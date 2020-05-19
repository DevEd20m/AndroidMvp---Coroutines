package com.deved.coroutinesmvp.data.repositories

import android.app.Application
import com.deved.coroutinesmvp.R
import com.deved.coroutinesmvp.common.isAvailableNetwork
import com.deved.coroutinesmvp.data.models.ErrorBody
import com.deved.coroutinesmvp.models.RequestLogin
import com.deved.coroutinesmvp.models.ResponseLogin
import com.deved.coroutinesmvp.rest.ApiClient
import com.deved.coroutinesmvp.rest.DataResponse
import com.google.gson.Gson
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import java.net.SocketTimeoutException

class MainRepositoryImpl(private val application: Application) : MainRepository {

    override suspend fun logIn(requestLogin: RequestLogin): DataResponse<ResponseLogin> =
        withContext(Dispatchers.IO) {

            try {
                if (!application.isAvailableNetwork()) return@withContext DataResponse.NetworkError
                val result = ApiClient.endPoints.logIn(requestLogin)
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

    override fun getMessageNullEmail(): String =
        application.resources.getString(R.string.emailIsNull)

    override fun getMessageNullPass(): String = application.resources.getString(R.string.passIsNull)


}