package com.example.proximateapp.presenter

import ProductInteractor
import com.example.proximateapp.contract.ProductContract
import com.example.proximateapp.entity.ProductData

class ProductPresenter(private val view: ProductContract.View) : ProductContract.Presenter {
    private val productInteractor: ProductInteractor = ProductInteractor()

    override fun getProductsPresenter(token: String) {
        view.showLoading()

        productInteractor.getProductsInteractor(
            token,
            object : ProductContract.Interactor.ProductCallback {
                override fun productInteractorSuccesses(productData: ProductData) {
                    view.hideLoading()
                    view.showProducts(productData)
                }

                override fun productInteractorError(error: String) {
                    view.hideLoading()
                    view.showProductError(error)
                }
            })
    }
}
