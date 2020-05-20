package com.deved.coroutinesmvp.ui.detail

import com.deved.coroutinesmvp.common.Scope
import com.deved.coroutinesmvp.data.models.detail.ResponsePlaces
import com.deved.coroutinesmvp.data.repositories.DetailRepository
import com.deved.coroutinesmvp.rest.DataResponse
import kotlinx.coroutines.launch

class DetailPresenter(
    var view: DetailMainContract.View?,
    var repository: DetailRepository
) : DetailMainContract.Presenter,
    Scope by Scope.Imp() {
    init {
        initScope()
        fetchPlaces()
    }

    override fun fetchPlaces() {
        launch {
            view?.showProgress()
            takeAction(repository.fetchPlaces())
            view?.hideProgress()
        }
    }

    private fun takeAction(result: DataResponse<ResponsePlaces>) {
        when (result) {
            is DataResponse.Success -> view?.updateUI(result.data.content?.places)
            is DataResponse.NetworkError -> view?.showErrorMessage(result.error)
            is DataResponse.ServerError -> view?.showErrorMessage(result.errorCode.message.toString())
            is DataResponse.TimeOutServerError -> view?.showErrorMessage(result.error)
            is DataResponse.ExceptionError -> view?.showErrorMessage(result.errorCode.message.toString())
        }
    }

    override fun onDestroyScope() {
        view = null
        destroyScope()
    }
}