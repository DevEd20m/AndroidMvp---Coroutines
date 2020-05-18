package com.deved.coroutinesmvp.data.repositories

import android.app.Application
import com.deved.coroutinesmvp.R
import com.deved.coroutinesmvp.common.isAvailableNetwork
import com.deved.coroutinesmvp.models.RequestLogin
import com.deved.coroutinesmvp.models.ResponseLogin
import com.deved.coroutinesmvp.rest.ApiClient
import com.deved.coroutinesmvp.rest.DataResponse
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
                    if (result.body() == null || result.body()?.content == null) return@withContext DataResponse.ServerError(
                        Exception()
                    )
                    return@withContext DataResponse.Success(result.body()!!)
                }
                return@withContext DataResponse.ServerError(Exception())
            }catch (e: SocketTimeoutException){
                return@withContext DataResponse.TimeOutError(application.resources.getString(R.string.serverNotResponding))
            }catch (e:Exception){
                return@withContext DataResponse.ServerError(e)
            }
        }

    override fun getMessageNullEmail(): String = application.resources.getString(R.string.emailIsNull)

    override fun getMessageNullPass(): String = application.resources.getString(R.string.passIsNull)


}