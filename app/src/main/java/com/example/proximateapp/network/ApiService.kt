package com.example.proximateapp.network

import retrofit2.Call
import com.example.proximateapp.entity.LoginRequest
import com.example.proximateapp.entity.LoginResponse
import com.example.proximateapp.entity.ProductRequest
import com.example.proximateapp.entity.ProductResponse
import retrofit2.http.Body
import retrofit2.http.Headers
import retrofit2.http.POST

interface ApiService {
    // Service  login
    @Headers("Content-Type: application/json")
    @POST("https://serveless.proximateapps-services.com.mx/proximatetools/dev/webadmin/testproximate/login")
    fun login(@Body request: LoginRequest): Call<LoginResponse>

    //Service Products
    @Headers("Content-Type: application/json")
    @POST("https://serveless.proximateapps-services.com.mx/proximatetools/dev/webadmin/testproximate/getproducts/")
    fun product(@Body request: ProductRequest): Call<ProductResponse>
}