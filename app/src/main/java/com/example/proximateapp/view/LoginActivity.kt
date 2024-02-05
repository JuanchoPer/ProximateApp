package com.example.proximateapp.view

import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.ProgressBar
import android.widget.Toast
import com.example.proximateapp.R
import com.example.proximateapp.contract.LoginContract
import com.example.proximateapp.presenter.LoginPresenter
import com.google.android.material.textfield.TextInputLayout


class LoginActivity : AppCompatActivity(), LoginContract.View {
    private lateinit var userName: EditText
    private lateinit var password: EditText
    private lateinit var loginButton: Button
    private lateinit var progressBar: ProgressBar
    private lateinit var presenter: LoginContract.Presenter
    private lateinit var tilUser: TextInputLayout
    private lateinit var tilPass: TextInputLayout

    override fun getContext(): Context {
        return this
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        userName = findViewById(R.id.et_user)
        password = findViewById(R.id.et_pass)
        loginButton = findViewById(R.id.bt_login)
        progressBar = findViewById(R.id.progressBar)
        tilUser = findViewById(R.id.til_user)
        tilPass = findViewById(R.id.til_pass)

        presenter = LoginPresenter(this, tilUser, tilPass)

        loginButton.setOnClickListener {
            val user = userName.text.toString()
            val pass = password.text.toString()
            presenter.login(user, pass)
        }
    }

    override fun showLoading() {
        progressBar.visibility = ProgressBar.VISIBLE
    }

    override fun hideLoading() {
        progressBar.visibility = ProgressBar.INVISIBLE
    }

    override fun showLoginSuccess() {
        Toast.makeText(this, "Inicio de sesion exitosa!", Toast.LENGTH_SHORT).show()
    }

    override fun showLoginError(error: String) {
        Toast.makeText(this, error, Toast.LENGTH_SHORT).show()
    }
}