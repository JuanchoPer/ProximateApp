package com.example.proximateapp.entity

import com.google.gson.annotations.SerializedName

data class LoginResponse(
    @SerializedName("status") val status: Boolean,
    @SerializedName("codeStatus") val codeStatus: String,
    @SerializedName("message") val mesagge: String,
    @SerializedName("data") val data: String?,
    @SerializedName("count") val count: Int
)

data class UserData (
    @SerializedName("id") val id: Int,
    @SerializedName("name") val name: String,
    @SerializedName("role") val role: String,
    @SerializedName("lastName") val lastName: String,
    @SerializedName("position") val position: String,
    @SerializedName("userToken") val userToken: String
)
