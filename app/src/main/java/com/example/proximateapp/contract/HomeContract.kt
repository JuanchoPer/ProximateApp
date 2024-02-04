package com.example.proximateapp.contract

import com.example.proximateapp.entity.ProductData

interface ProductContract {
    interface View {
        fun showLoading()
        fun hideLoading()
        fun showProducts(productData: ProductData)
        fun showProductError(error: String)
    }

    interface Presenter {
        fun getProductsPresenter(token: String)
    }

    interface Interactor {
        interface ProductCallback {
            fun productInteractorSuccesses(productData: ProductData)
            fun productInteractorError(error: String)
        }

        fun getProductsInteractor(token: String, callback: ProductCallback)
    }
}
