package com.example.proximateapp.contract

import android.content.Context

interface LoginContract {
    interface View {
        fun getContext(): Context
        fun showLoading()
        fun hideLoading()
        fun showLoginSuccess()
        fun showLoginError(error: String)
    }

    interface Presenter {
        fun login(username: String, password: String)
    }
}