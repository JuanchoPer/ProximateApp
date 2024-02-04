package com.example.proximateapp.view

import ProductAdapter
import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.drawerlayout.widget.DrawerLayout
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.android.material.navigation.NavigationView
import androidx.recyclerview.widget.RecyclerView
import com.example.proximateapp.R
import com.example.proximateapp.contract.ProductContract
import com.example.proximateapp.entity.ProductData
import com.example.proximateapp.presenter.ProductPresenter

class HomeActivity : AppCompatActivity(), ProductContract.View {

    private lateinit var drawerLayout: DrawerLayout
    private lateinit var navigationView: NavigationView
    private lateinit var recyclerView: RecyclerView
    private lateinit var productAdapter: ProductAdapter
    private val productPresenter = ProductPresenter(this)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)

        drawerLayout = findViewById(R.id.drawer_layout)
        navigationView = findViewById(R.id.navigation_view)
        recyclerView = findViewById(R.id.recycler_view_products)

        val toggle = ActionBarDrawerToggle(this, drawerLayout, 0, 0)
        drawerLayout.addDrawerListener(toggle)
        toggle.syncState()

        val sharedPreferences = getSharedPreferences("login", Context.MODE_PRIVATE)
        val token = sharedPreferences.getString("userToken", null)

        productAdapter = ProductAdapter()
        recyclerView.adapter = productAdapter
        recyclerView.layoutManager = LinearLayoutManager(this)

        token?.let {
            productPresenter.getProductsPresenter(it)
        }
    }

    override fun showLoading() {
        // Implementa la lógica para mostrar una vista de carga o indicador de progreso
    }

    override fun hideLoading() {
        // Implementa la lógica para ocultar la vista de carga o indicador de progreso
    }

    override fun showProducts(productData: ProductData) {
        productAdapter.setProducts(productData.products ?: emptyList())
    }

    override fun showProductError(error: String) {
        // Implementa la lógica para mostrar un mensaje de error al usuario
    }
}
