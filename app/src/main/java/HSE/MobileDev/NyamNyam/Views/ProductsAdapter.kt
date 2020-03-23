package HSE.MobileDev.NyamNyam.Views

import HSE.MobileDev.NyamNyam.App
import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.ImageView
import android.widget.TextView
import java.util.*


import HSE.MobileDev.NyamNyam.Database.Model.Product
import HSE.MobileDev.NyamNyam.R

class ProductsAdapter// 1
(private val mContext: Context, private val products: List<Product>) : BaseAdapter() {

    // 2
    override fun getCount(): Int {
        return products.size
    }

    // 3
    override fun getItemId(position: Int): Long {
        return 0
    }

    // 4
    override fun getItem(position: Int): Any? {
        return null
    }

    // 5
    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {
        var convertView = convertView
        val product = products[position]

        // view holder pattern
        if (convertView == null) {
            val layoutInflater = LayoutInflater.from(mContext)
            convertView = layoutInflater.inflate(R.layout.linearlayout_product, null)

            val imageViewCoverArt = convertView!!.findViewById<View>(R.id.imageViewCoverArt) as ImageView
            val nameTextView = convertView.findViewById<View>(R.id.nameTextView) as TextView

            val viewHolder = ViewHolder(nameTextView, imageViewCoverArt)
            convertView.tag = viewHolder

        }



        val viewHolder = convertView.tag as ViewHolder
        val uri = "@drawable/" + product.imageSource

        var imageResource = App.getResource()?.getIdentifier(uri, null, "HSE.MobileDev.NyamNyam")
        if (imageResource == null) imageResource = 0
        viewHolder.imageViewCoverArt.setImageResource(imageResource)
        viewHolder.nameTextView.text = product.name

        return convertView
    }

    class ViewHolder(val nameTextView: TextView, val imageViewCoverArt: ImageView)

}
