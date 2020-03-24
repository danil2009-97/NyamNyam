package HSE.MobileDev.NyamNyam

import HSE.MobileDev.NyamNyam.Database.DatabaseHelper
import HSE.MobileDev.NyamNyam.Database.Model.Product
import HSE.MobileDev.NyamNyam.Database.Model.Recipe
import HSE.MobileDev.NyamNyam.Views.ProductSimpleAdapter
import HSE.MobileDev.NyamNyam.Views.ProductsAdapter
import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.GridView
import androidx.appcompat.app.AppCompatActivity
import java.util.*
import android.widget.AdapterView
import android.widget.AdapterView.OnItemClickListener
import androidx.appcompat.app.AlertDialog
import androidx.core.content.ContextCompat.startActivity


class MainActivity : AppCompatActivity() {

    private var productsAdapter : ProductsAdapter? = null
    private var gridView : GridView? = null

    private val productList: MutableList<Product> = ArrayList()
    private var db: DatabaseHelper? = null


    private var products = ArrayList(
            Arrays.asList(
                    Product(0, "Bread", "bread",
                            1),
                    Product(1, "Banana", "banana",
                            1),
                    Product(2, "Sausage", "sausage",
                            1),
                    Product(2, "Sausage", "sausage",
                            1),
                    Product(2, "Sausage", "sausage",
                            1),
                    Product(2, "Sausage", "sausage",
                            1),
                    Product(2, "Sausage", "sausage",
                            1)
            ))


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        db = DatabaseHelper(this)
        productList.addAll(db!!.allProducts)

        val recipe = Recipe(1,"Рецепт молочка","http", "очень вкусное", 1, "берешь и делаешь");
        val recipe_1 = db!!.createRecipe(recipe);

        val product = Product(0, "Bread", "bread",
                1);
        val product_1: Long = db!!.createProduct(product, longArrayOf(1))
//        db!!.createProductRecipe(1,1);

        gridView = findViewById<View>(R.id.gridview) as GridView
        val productsAdapter = ProductsAdapter(this, products)
        gridView!!.adapter = productsAdapter

        gridView!!.onItemClickListener = OnItemClickListener { parent, view, position, id ->
            // Get the GridView selected/clicked item text
            showActionsDialog(position)
        };
    }

    private fun showActionsDialog(position: Int) {
        val colors = arrayOf<CharSequence>("Да", "Нет")

        val builder = AlertDialog.Builder(this)
        builder.setTitle("Удалить продукт?")
        builder.setItems(colors) { dialog, which ->
            if (which == 0) {
                deleteProductFromFridge(position)
                productsAdapter = ProductsAdapter(this, products)
                gridView!!.adapter = productsAdapter
            }
        }
        builder.show()
    }

    private fun deleteProductFromFridge(position: Int){
        products.removeAt(position)
    }

    fun openRecipe(view: View) {
        val recipeIntent = Intent(this, RecipeActivity::class.java)

        startActivity(recipeIntent)
    }

    fun openProducts(view: View){
        val productIntent = Intent(this, ProductsActivity::class.java)
        startActivity(productIntent)
    }

}


