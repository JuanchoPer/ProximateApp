package com.example.proximateapp.interactor

import com.example.proximateapp.entity.LoginRequest
import com.example.proximateapp.entity.LoginResponse
import com.example.proximateapp.network.ApiService
import com.example.proximateapp.network.RetrofitClient
import org.json.JSONException
import org.json.JSONObject
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import com.example.proximateapp.view.HomeActivity
import com.google.android.material.textfield.TextInputLayout


class LoginInteractor(private val context: Context) {
    private val apiService: ApiService = RetrofitClient.apiService

    interface LoginCallback {
        fun loginInteractorSuccess()
        fun loginInteractorError(error: String)
    }

    fun login(username: String, password: String, callback: LoginCallback) {
        val request = LoginRequest(username, password)
        val call: Call<LoginResponse> = apiService.login(request)


        call.enqueue(object : Callback<LoginResponse> {
            override fun onResponse(call: Call<LoginResponse>, response: Response<LoginResponse>) {
                if (response.isSuccessful) {
                    val loginResponse = response.body()
                    if (loginResponse != null) {
                        val responseData = loginResponse.data
                        if (responseData != null) {
                            if (responseData.isNotBlank()) {
                                try {
                                    val jsonData = JSONObject(responseData)
                                    val token = jsonData.getString("userToken")

                                    val sharePreferences =
                                        context.getSharedPreferences("login", Context.MODE_PRIVATE)
                                    val edit: SharedPreferences.Editor = sharePreferences.edit()
                                    edit.putString("userToken", token)
                                    edit.apply()

                                    val intent = Intent(context, HomeActivity::class.java)
                                    context.startActivity(intent)

                                    callback.loginInteractorSuccess()
                                } catch (e: JSONException) {
                                    callback.loginInteractorError("Error al procesar la respuesta JSON")
                                }
                            } else {
                                callback.loginInteractorError("La respuesta no contiene datos válidos")
                            }
                        } else {
                            callback.loginInteractorError(loginResponse.mesagge)
                        }
                    } else {
                        callback.loginInteractorError("Respuesta vacía del servidor")
                    }
                } else {
                    callback.loginInteractorError("Error en la respuesta del servidor")
                }
            }

            override fun onFailure(call: Call<LoginResponse>, t: Throwable) {
                callback.loginInteractorError("Error de red ${t.message}")
            }
        })
    }
}
