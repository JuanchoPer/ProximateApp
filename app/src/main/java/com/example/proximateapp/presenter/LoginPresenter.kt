package com.example.proximateapp.presenter

import android.content.Context
import com.example.proximateapp.contract.LoginContract
import com.example.proximateapp.interactor.LoginInteractor

class LoginPresenter(private val view: LoginContract.View) : LoginContract.Presenter {

    private val loginInteractor: LoginInteractor = LoginInteractor(view.getContext())

    override fun login(username: String, password: String) {
        view.showLoading()

        loginInteractor.login(username, password, object : LoginInteractor.LoginCallback {
            override fun loginInteractorSuccess() {
                view.hideLoading()
                view.showLoginSuccess()
            }

            override fun loginInteractorError(error: String) {
                view.hideLoading()
                view.showLoginError("error")
            }
        })
    }
}