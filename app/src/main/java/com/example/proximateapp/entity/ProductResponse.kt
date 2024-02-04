package com.example.proximateapp.entity

data class ProductResponse(
    val status: Boolean,
    val codeStatus: String,
    val message: String,
    val data: String
)

data class ProductData(
    val menu: List<Menu>,
    val products: List<Product>
)

data class Menu(
    val id: Int,
    val icon: String,
    val productId: Int,
    val redirectTo: String
)

data class Product(
    val id: Int,
    val path: String,
    val image: String,
    val title: String,
    val longDescription: String,
    val shortDescription: String
)

