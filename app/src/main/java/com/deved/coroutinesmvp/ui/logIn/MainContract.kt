package com.deved.coroutinesmvp.ui.logIn

import com.deved.coroutinesmvp.common.ContractBase
import com.deved.coroutinesmvp.models.RequestLogin


interface MainContract {
    interface View{
        fun showProgress()
        fun hideProgress()
        fun showErrorMessage(error:String)
        fun showNetworkError()
        fun navigateToDetail()
    }
    interface Presenter :ContractBase{
        fun logInUser(requestLogin: RequestLogin)
        fun validateInputLogin(email:String?, password:String?)
    }
}
