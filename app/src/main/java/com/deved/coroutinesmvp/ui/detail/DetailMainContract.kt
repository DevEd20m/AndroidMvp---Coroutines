package com.deved.coroutinesmvp.ui.detail

import com.deved.coroutinesmvp.common.ContractBase
import com.deved.coroutinesmvp.data.models.detail.ContentResponsePlace

interface DetailMainContract {
    interface View {
        fun showProgress()
        fun hideProgress()
        fun showErrorMessage(error:String)
        fun updateUI(places: List<ContentResponsePlace>?)
    }

    interface Presenter:ContractBase {
        fun fetchPlaces()
    }
}