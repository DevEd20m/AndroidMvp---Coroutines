package com.deved.coroutinesmvp.ui.logIn

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import com.deved.coroutinesmvp.R
import com.deved.coroutinesmvp.common.toast
import com.deved.coroutinesmvp.data.repositories.MainRepositoryImpl
import com.deved.coroutinesmvp.ui.detail.DetailActivity
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity(), MainContract.View {
    private lateinit var presenter: MainPresenter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        presenter = MainPresenter(this, MainRepositoryImpl(application))

        button_loginApp.setOnClickListener {
            val email = editText_user.text.toString().trim()
            val pass = editText_password.text.toString().trim()
            presenter.validateInputLogin(email, pass)
        }
    }

    override fun showProgress() {
        progressbar_login.visibility = View.VISIBLE
    }

    override fun hideProgress() {
        progressbar_login.visibility = View.GONE
    }

    override fun showErrorMessage(error: String) {
        toast(error)
    }

    override fun showNetworkError() {
        toast(this.resources.getString(R.string.errorNetwork))
    }

    override fun navigateToDetail() {
        startActivity(Intent(this, DetailActivity::class.java))
    }

    override fun onDestroy() {
        super.onDestroy()
        presenter.onDestroyScope()
    }
}
