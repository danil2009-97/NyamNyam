package HSE.MobileDev.NyamNyam

import HSE.MobileDev.NyamNyam.Database.DatabaseHelper
import HSE.MobileDev.NyamNyam.Database.Model.Product
import HSE.MobileDev.NyamNyam.Database.Model.Recipe
import HSE.MobileDev.NyamNyam.Views.ProductsAdapter
import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.GridView
import androidx.appcompat.app.AppCompatActivity
import java.util.*


class MainActivity : AppCompatActivity() {

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

        val gridView = findViewById<View>(R.id.gridview) as GridView
        val productsAdapter = ProductsAdapter(this, products)
        gridView.adapter = productsAdapter
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


