package HSE.MobileDev.NyamNyam

import HSE.MobileDev.NyamNyam.Database.DatabaseHelper
import HSE.MobileDev.NyamNyam.Database.Model.Product
import HSE.MobileDev.NyamNyam.Utils.RecycleTouchListener
import HSE.MobileDev.NyamNyam.Views.ProductSimpleAdapter
import android.os.Bundle
import android.provider.ContactsContract.CommonDataKinds.Note
import android.view.View
import android.widget.TextView
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.DefaultItemAnimator
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import java.util.*
import kotlin.collections.ArrayList


class ProductsActivity : AppCompatActivity(){

    private var productsAdapter: ProductSimpleAdapter? = null
    private var productsList = ArrayList<Product>()
    private var recyclerView: RecyclerView? = null
    private var noProductsView: TextView? = null
    var db: DatabaseHelper? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.layout_available_products)
        db = DatabaseHelper(this)

        noProductsView = findViewById<TextView>(R.id.empty_products_view) as TextView

        productsList = getProductsList()
        toggleEmptyProducts()

        recyclerView = findViewById<View>(R.id.recycler_products) as RecyclerView
        val mLayoutManager = LinearLayoutManager(applicationContext)
        recyclerView!!.layoutManager = mLayoutManager
        recyclerView!!.itemAnimator = DefaultItemAnimator()
        productsAdapter = ProductSimpleAdapter(this, productsList)
        recyclerView!!.adapter = productsAdapter

        var thisView = recyclerView!!
        recyclerView!!.addOnItemTouchListener(RecycleTouchListener(this,
                thisView, object : RecycleTouchListener.ClickListener {
            override fun onClick(view: View, position: Int) {}

            override fun onLongClick(view: View?, position: Int) {
                showActionsDialog(position)
            }
        }))
    }

    private fun showActionsDialog(position: Int) {
        val colors = arrayOf<CharSequence>("Да", "Нет")

        val builder = AlertDialog.Builder(this)
        builder.setTitle("Добавить продукт?")
        builder.setItems(colors) { dialog, which ->
            if (which == 0) {
                addProductToFridge(position)
                productsAdapter = ProductSimpleAdapter(this, productsList)
                recyclerView!!.adapter = productsAdapter
            } else {

            }
        }
        builder.show()
    }

    private fun addProductToFridge(position: Int){

        val p: Product = productsList.get(position)
        p.isAvailable = 1
        db = DatabaseHelper(this)
        db!!.updateProduct(p)
        //update продукта поставить ему isavailable = 1
        //по индексу получить продукт, взять его id, проапдейтить в базе
        productsList.removeAt(position)
    }

    private fun toggleEmptyProducts() {

        if (productsList.size > 0) {
            noProductsView!!.visibility = View.GONE
        } else {
            noProductsView!!.visibility = View.VISIBLE
        }
    }

    // select products where available = 0 (!!!)
    private fun getProductsList (): ArrayList<Product>{

        var productsList: ArrayList<Product> = db!!.allProducts
        return productsList
//        return ArrayList(Arrays.asList(
//                Product(0, "Bread", "bread",
//                        1),
//                Product(1, "Banana", "banana",
//                        1),
//                Product(2, "Sausage", "sausage",
//                        1),
//                Product(2, "Sausage", "sausage",
//                        1),
//                Product(2, "Sausage", "sausage",
//                        1),
//                Product(2, "Sausage", "sausage",
//                        1),
//                Product(2, "Sausage", "sausage",
//                        1)))
    }

    fun closeProductsActivity(view: View) {
        super.onBackPressed()

    }
}