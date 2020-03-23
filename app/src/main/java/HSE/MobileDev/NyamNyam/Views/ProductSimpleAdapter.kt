package HSE.MobileDev.NyamNyam.Views

import HSE.MobileDev.NyamNyam.Database.Model.Product
import HSE.MobileDev.NyamNyam.R
import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class ProductSimpleAdapter (private val context: Context, private val productsList: List<Product>) : RecyclerView.Adapter<ProductSimpleAdapter.MyViewHolder>() {

    inner class MyViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        var productName: TextView

        init {
            productName = view.findViewById(R.id.product_name)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val itemView = LayoutInflater.from(parent.context)
                .inflate(R.layout.product_simple_row, parent, false)

        return MyViewHolder(itemView)
    }

    override fun getItemCount(): Int {
        return productsList.size
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val product = productsList[position]

        holder.productName.text = product.name
    }
}