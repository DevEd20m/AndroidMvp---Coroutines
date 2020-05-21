package com.deved.coroutinesmvp.ui.detail

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import com.deved.coroutinesmvp.R
import com.deved.coroutinesmvp.common.toast
import com.deved.coroutinesmvp.data.models.detail.ContentResponsePlace
import com.deved.coroutinesmvp.data.repositories.DetailRepositoryImpl
import kotlinx.android.synthetic.main.activity_detail.*

class DetailActivity : AppCompatActivity(), DetailMainContract.View {


    private lateinit var presenter: DetailPresenter
    private val adapter by lazy { DetailAdapter() }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail)
        presenter = DetailPresenter(this, DetailRepositoryImpl(application))
        recyclerView_detail.adapter = adapter
    }

    override fun showProgress() {
        progressbar_detail.visibility = View.VISIBLE
    }

    override fun hideProgress() {
        progressbar_detail.visibility = View.GONE
    }

    override fun showErrorMessage(error: String) {
        toast(error)
    }

    override fun updateUI(places: List<ContentResponsePlace>?) {
        places?.let {
            adapter.placeList = places
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        presenter.onDestroyScope()
    }

    companion object {
        val TAG = DetailActivity::class.java.name
    }
}
