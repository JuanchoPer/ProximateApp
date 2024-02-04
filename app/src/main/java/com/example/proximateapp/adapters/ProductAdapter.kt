import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.proximateapp.R
import com.example.proximateapp.entity.Product

class ProductAdapter : RecyclerView.Adapter<ProductAdapter.ProductViewHolder>() {

    private val products: MutableList<Product> = mutableListOf()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ProductViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_product, parent, false)
        return ProductViewHolder(view)
    }

    override fun onBindViewHolder(holder: ProductViewHolder, position: Int) {
        val product = products[position]
        holder.bind(product)
    }

    override fun getItemCount(): Int {
        return products.size
    }

    fun setProducts(productList: List<Product>) {
        products.clear()
        products.addAll(productList)
        notifyDataSetChanged()
    }


    inner class ProductViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val titleTextView: TextView = itemView.findViewById(R.id.tv_title)
        private val imageView: ImageView = itemView.findViewById(R.id.iv_image)
        private val descriptionTextView: TextView = itemView.findViewById(R.id.tv_description)

        fun bind(product: Product) {
            titleTextView.text = product.title
            descriptionTextView.text = product.shortDescription
            Glide.with(itemView.context)
                .load(product.image) // Debes asegurarte de que product.image sea una URL válida o un recurso de imagen
                .placeholder(R.drawable.ic_image_default)
                .error(R.drawable.ic_image_default)
                .into(imageView)
        }
    }
}
