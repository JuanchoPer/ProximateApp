package com.example.proximateapp.view

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageView
import android.widget.TextView
import com.bumptech.glide.Glide
import com.example.proximateapp.R

class ProductDetailActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_product_detail)

        val ivBack = findViewById<ImageView>(R.id.iv_back)
        ivBack.setOnClickListener {
            val intent = Intent(this, HomeActivity::class.java)
            startActivity(intent)
            finish()
        }

        val productId = intent.getIntExtra("productId", 0)
        val productName = intent.getStringExtra("productName")
        val productDescription = intent.getStringExtra("productDescription")
        val productImage = intent.getStringExtra("productImage")

        val tvDetailTitle = findViewById<TextView>(R.id.tv_detail_title)
        val ivDetail = findViewById<ImageView>(R.id.iv_detail)
        val tvDetailDescription = findViewById<TextView>(R.id.tv_detail_description)

        tvDetailTitle.text = productName
        tvDetailDescription.text = productDescription

        Glide.with(this)
            .load(productImage)
            .placeholder(R.drawable.ic_image_default) // Puedes establecer una imagen de carga predeterminada
            .error(R.drawable.ic_no_image) // Puedes establecer una imagen de error si la carga falla
            .into(ivDetail)
    }
}