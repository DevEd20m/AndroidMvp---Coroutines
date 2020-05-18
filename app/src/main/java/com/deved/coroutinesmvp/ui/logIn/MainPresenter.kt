package com.deved.coroutinesmvp.ui.logIn

import com.deved.coroutinesmvp.common.Scope
import com.deved.coroutinesmvp.data.repositories.MainRepository
import com.deved.coroutinesmvp.models.RequestLogin
import com.deved.coroutinesmvp.models.ResponseLogin
import com.deved.coroutinesmvp.rest.DataResponse
import kotlinx.coroutines.launch

class MainPresenter(
    private var view: MainContract.View?,
    private var repository: MainRepository
) : MainContract.Presenter, Scope by Scope.Imp() {

    init {
        initScope()
    }

    override fun logInUser(requestLogin: RequestLogin) {
        launch {
            view?.showProgress()
            doAction(repository.logIn(requestLogin))
            view?.hideProgress()
        }
    }

    private fun doAction(result: DataResponse<ResponseLogin>) {
        when (result) {
            is DataResponse.Success -> view?.navigateToDetail()
            DataResponse.NetworkError -> view?.showNetworkError()
            is DataResponse.TimeOutError -> view?.showErrorMessage(result.error)
            is DataResponse.ServerError -> view?.showErrorMessage(result.errorCode.message.toString())
        }
    }

    override fun validateInputLogin(email: String?, password: String?) {
        if (email == null || email.isEmpty()) {
            view?.showErrorMessage(repository.getMessageNullEmail())
        } else if (password == null || password.isEmpty()) {
            view?.showErrorMessage(repository.getMessageNullPass())
        } else {
            logInUser(RequestLogin(email, password))
        }
    }

    override fun onDestroyScope() {
        view = null
        destroyScope()
    }
}