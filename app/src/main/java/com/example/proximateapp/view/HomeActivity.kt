package com.example.proximateapp.view

import ProductAdapter
import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageView
import android.widget.ProgressBar
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.core.view.GravityCompat
import androidx.drawerlayout.widget.DrawerLayout
import androidx.recyclerview.widget.GridLayoutManager
import com.google.android.material.navigation.NavigationView
import androidx.recyclerview.widget.RecyclerView
import com.example.proximateapp.R
import com.example.proximateapp.contract.ProductContract
import com.example.proximateapp.entity.Product
import com.example.proximateapp.entity.ProductData
import com.example.proximateapp.presenter.ProductPresenter

class HomeActivity : AppCompatActivity(), ProductContract.View {

    private lateinit var drawerLayout: DrawerLayout
    private lateinit var navigationView: NavigationView
    private lateinit var recyclerView: RecyclerView
    private lateinit var progressBarHome: ProgressBar
    private lateinit var productAdapter: ProductAdapter
    private val productPresenter = ProductPresenter(this)

    private lateinit var drawerToggle: ActionBarDrawerToggle


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)

        val sharedPreferences = getSharedPreferences("login", Context.MODE_PRIVATE)
        val token = sharedPreferences.getString("userToken", null)

        drawerLayout = findViewById(R.id.drawer_layout)
        navigationView = findViewById(R.id.navigation_view)
        recyclerView = findViewById(R.id.recycler_view_products)
        progressBarHome = findViewById(R.id.progressBarHome)

        val ivLogout = findViewById<ImageView>(R.id.iv_logout)
        val ivMenu = findViewById<ImageView>(R.id.iv_menu)

        drawerToggle = ActionBarDrawerToggle(
            this, drawerLayout,
            R.string.navigation_drawer_open, R.string.navigation_drawer_close
        )
        drawerLayout.addDrawerListener(drawerToggle)
        drawerToggle.syncState()

        ivMenu.setOnClickListener {
            if (drawerLayout.isDrawerOpen(GravityCompat.START)) {
                drawerLayout.closeDrawer(GravityCompat.START)
            } else {
                drawerLayout.openDrawer(GravityCompat.START)
            }
        }

        ivLogout.setOnClickListener {
            val editor = sharedPreferences.edit()
            editor.remove("userToken")
            editor.apply()

            val intent = Intent(this, LoginActivity::class.java)
            startActivity(intent)
            finish()
        }

        val toggle = ActionBarDrawerToggle(
            this, drawerLayout,
            R.string.navigation_drawer_open, R.string.navigation_drawer_close
        )
        drawerLayout.addDrawerListener(toggle)
        toggle.syncState()

        productAdapter = ProductAdapter()
        recyclerView.adapter = productAdapter

        recyclerView.layoutManager = GridLayoutManager(this, 2)

        token?.let {
            productPresenter.getProductsPresenter(it)
        }

        navigationView.setNavigationItemSelectedListener { menuItem ->
            val product = getProductFromMenuItem(menuItem.itemId)
            if (product != null) {
                val intent = Intent(this, ProductDetailActivity::class.java)
                intent.putExtra("productId", product.id)
                intent.putExtra("productName", product.title)
                intent.putExtra("productDescription", product.longDescription)
                intent.putExtra("productImage", product.image)
                startActivity(intent)
            }
            true
        }
    }

    override fun showLoading() {
        progressBarHome.visibility = ProgressBar.VISIBLE
    }

    override fun hideLoading() {
        progressBarHome.visibility = ProgressBar.INVISIBLE
    }

    override fun showProducts(productData: ProductData) {
        productAdapter.setProducts(productData.products ?: emptyList())
        configureMenu(productData.products)
    }

    override fun showProductError(error: String) {}
    private fun configureMenu(menuList: List<Product>) {
        val menu = navigationView.menu
        menu.clear()
        for (item in menuList) {
            menu.add(0, item.id, 0, item.title)
        }
    }

    private fun getProductFromMenuItem(menuItemId: Int): Product? {
        for (product in productAdapter.getProducts()) {
            if (product.id == menuItemId) {
                return product
            }
        }
        return null
    }
}
