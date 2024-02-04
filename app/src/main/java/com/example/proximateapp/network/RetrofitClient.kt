package com.example.proximateapp.network

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitClient {
    private const val BASE_URL =
        "https://serveless.proximateapps-services.com.mx/proximatetools/dev/webadmin/testproximate/login/"

    private val retrofit: Retrofit = Retrofit.Builder()
        .baseUrl(BASE_URL)
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    val apiService: ApiService = retrofit.create(ApiService::class.java)
}

object RetrofitClient2 {
    private const val BASE_URL =
        "https://serveless.proximateapps-services.com.mx/proximatetools/dev/webadmin/testproximate/getproducts/"

    private val retrofit2: Retrofit = Retrofit.Builder()
        .baseUrl(BASE_URL)
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    val apiService: ApiService = retrofit2.create(ApiService::class.java)
}
