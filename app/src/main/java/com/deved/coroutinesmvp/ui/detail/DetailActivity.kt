package com.deved.coroutinesmvp.ui.detail

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import com.deved.coroutinesmvp.R
import com.deved.coroutinesmvp.common.toast
import com.deved.coroutinesmvp.data.models.detail.ContentResponsePlace
import com.deved.coroutinesmvp.data.repositories.DetailRepositoryImpl
import kotlinx.android.synthetic.main.activity_detail.*

class DetailActivity : AppCompatActivity(), DetailMainContract.View {
    companion object{
        val TAG = DetailActivity::class.java.name
    }
    private lateinit var presenter: DetailPresenter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail)
        presenter = DetailPresenter(this,DetailRepositoryImpl(application))
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
            for(value : ContentResponsePlace in places){
                Log.d(TAG,value.nameTourist.toString())
            }
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        presenter.onDestroyScope()
    }
}
