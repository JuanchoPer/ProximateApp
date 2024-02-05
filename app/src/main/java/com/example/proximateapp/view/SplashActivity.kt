package com.example.proximateapp.view

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import com.example.proximateapp.R

class SplashActivity : AppCompatActivity() {
    private val SPLASH_DELAY: Long = 2000
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)

        val sharedPreferences = getSharedPreferences("login", Context.MODE_PRIVATE)
        val token = sharedPreferences.getString("userToken", null)

        val handler = Handler(Looper.getMainLooper())

        handler.postDelayed({
            if (token != null && isValidToken(token)) {
                val intent = Intent(this, HomeActivity::class.java)
                startActivity(intent)
                finish()
            } else {
                val loginIntent = Intent(this, LoginActivity::class.java)
                startActivity(loginIntent)
                finish()
            }
        }, SPLASH_DELAY)
    }

    private fun isValidToken(token: String): Boolean {
        return true
    }
}