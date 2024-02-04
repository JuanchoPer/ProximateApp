package com.example.proximateapp.view

import ProductAdapter
import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.appcompat.widget.Toolbar
import androidx.drawerlayout.widget.DrawerLayout
import androidx.recyclerview.widget.GridLayoutManager
import com.google.android.material.navigation.NavigationView
import androidx.recyclerview.widget.RecyclerView
import com.example.proximateapp.R
import com.example.proximateapp.contract.ProductContract
import com.example.proximateapp.entity.Menu
import com.example.proximateapp.entity.Product
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
        val toolbar = findViewById<Toolbar>(R.id.toolbar)

        setSupportActionBar(toolbar)

        val toggle = ActionBarDrawerToggle(
            this, drawerLayout, toolbar,
            R.string.navigation_drawer_open, R.string.navigation_drawer_close
        )
        drawerLayout.addDrawerListener(toggle)
        toggle.syncState()

        val sharedPreferences = getSharedPreferences("login", Context.MODE_PRIVATE)
        val token = sharedPreferences.getString("userToken", null)

        productAdapter = ProductAdapter()
        recyclerView.adapter = productAdapter

        recyclerView.layoutManager = GridLayoutManager(this, 2)

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
        configureMenu(productData.products)
    }

    override fun showProductError(error: String) {
        // Implementa la lógica para mostrar un mensaje de error al usuario
    }

    private fun configureMenu(menuList: List<Product>) {
        val menu = navigationView.menu

        // Limpiamos el menú actual
        menu.clear()

        // Agregamos los elementos del menú basados en los datos obtenidos del servicio
        for (item in menuList) {
            menu.add(0, item.id, 0, item.title)
        }
    }
}
