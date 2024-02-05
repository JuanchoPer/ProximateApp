import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.proximateapp.R
import com.example.proximateapp.entity.Product
import com.example.proximateapp.view.ProductDetailActivity

class ProductAdapter : RecyclerView.Adapter<ProductAdapter.ProductViewHolder>() {

    private val products: MutableList<Product> = mutableListOf()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ProductViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_product_card, parent, false)
        return ProductViewHolder(view)
    }

    fun getProducts(): List<Product> {
        return products
    }
    override fun onBindViewHolder(holder: ProductViewHolder, position: Int) {
        val product = products[position]
        holder.bind(product)

        holder.itemView.setOnClickListener {
            val intent = Intent(holder.itemView.context, ProductDetailActivity::class.java)

            intent.putExtra("productId", product.id)
            intent.putExtra("productName", product.title)
            intent.putExtra("productDescription", product.longDescription)
            intent.putExtra("productImage", product.image)

            holder.itemView.context.startActivity(intent)
        }
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
                .load(product.image)
                .placeholder(R.drawable.ic_image_default)
                .error(R.drawable.ic_no_image)
                .into(imageView)
        }
    }
}
