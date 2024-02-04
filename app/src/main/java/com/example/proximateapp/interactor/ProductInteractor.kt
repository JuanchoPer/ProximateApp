import android.util.Log
import com.example.proximateapp.contract.ProductContract
import com.example.proximateapp.entity.ProductData
import com.example.proximateapp.entity.ProductRequest
import com.example.proximateapp.entity.ProductResponse
import com.example.proximateapp.network.ApiService
import com.example.proximateapp.network.RetrofitClient2
import com.google.gson.Gson
import retrofit2.Call
import retrofit2.Callback
import retrofit2.HttpException
import retrofit2.Response

class ProductInteractor : ProductContract.Interactor {
    private val apiService: ApiService = RetrofitClient2.apiService

    override fun getProductsInteractor(
        token: String,
        callback: ProductContract.Interactor.ProductCallback
    ) {
        val productRequest = ProductRequest(token)

        apiService.product(productRequest).enqueue(object : Callback<ProductResponse> {
            override fun onResponse(
                call: Call<ProductResponse>,
                response: Response<ProductResponse>
            ) {
                if (response.isSuccessful) {
                    val productResponse = response.body()
                    if (productResponse != null) {
                        val data = productResponse.data
                        if (data != null && data.isNotBlank() && data.startsWith("{") && data.endsWith("}")) {
                            val productData = Gson().fromJson(data, ProductData::class.java)
                            callback.productInteractorSuccesses(productData)
                        } else {
                            callback.productInteractorError("Respuesta nula o no válida del servidor")
                        }
                    } else {
                        callback.productInteractorError("Respuesta nula del servidor")
                    }
                } else {
                    callback.productInteractorError("Error obteniendo productos")
                }
            }

            override fun onFailure(call: Call<ProductResponse>, t: Throwable) {
                val errorBody = (t as? HttpException)?.response()?.errorBody()?.string()
                Log.e("ProductInteractor", "Error de red: $errorBody")
                callback.productInteractorError("Error de red")
            }
        })
    }
}
