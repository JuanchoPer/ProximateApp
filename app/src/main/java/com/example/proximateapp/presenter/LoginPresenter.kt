package com.example.proximateapp.presenter

import android.content.Context
import com.example.proximateapp.contract.LoginContract
import com.example.proximateapp.interactor.LoginInteractor
import com.google.android.material.textfield.TextInputLayout

class LoginPresenter(
    private val view: LoginContract.View,
    private val tilUser: TextInputLayout,
    private val tilPass: TextInputLayout
) : LoginContract.Presenter {

    private val loginInteractor: LoginInteractor = LoginInteractor(view.getContext())

    override fun login(username: String, password: String) {
        view.showLoading()
        hideError()

        if ((username.isNullOrEmpty() || username == "undefined") || (password.isNullOrEmpty() || password == "undefined")) {
            if (username.isNullOrEmpty()) {
                setErrorAndFocus(tilUser, "El usuario no puede estar vacío")
                view.hideLoading()
                return
            } else if (password.isNullOrEmpty()) {
                setErrorAndFocus(tilPass, "La contraseña no puede estar vacía")
                view.hideLoading()
                return
            }
        } else {
            loginInteractor.login(username, password, object : LoginInteractor.LoginCallback {
                override fun loginInteractorSuccess() {
                    view.hideLoading()
                    view.showLoginSuccess()
                }

                override fun loginInteractorError(error: String) {
                    view.hideLoading()
                    view.showLoginError(error)
                }
            })
        }
    }

    private fun hideError() {
        tilUser.isErrorEnabled = false
        tilPass.isErrorEnabled = false
    }

    private fun setErrorAndFocus(textInputLayout: TextInputLayout, errorMessage: String) {
        textInputLayout.isErrorEnabled = true
        textInputLayout.error = errorMessage
        textInputLayout.editText?.requestFocus()
    }
}