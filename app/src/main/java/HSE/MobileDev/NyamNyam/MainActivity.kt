package HSE.MobileDev.NyamNyam

import HSE.MobileDev.NyamNyam.Database.Model.AppDatabase
import androidx.appcompat.app.AppCompatActivity

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.GridView

import java.util.ArrayList
import java.util.Arrays

import HSE.MobileDev.NyamNyam.Database.Model.Product
import HSE.MobileDev.NyamNyam.Database.Model.Recipe
import HSE.MobileDev.NyamNyam.Views.ProductsAdapter
import androidx.room.Room

class MainActivity : AppCompatActivity() {

    private val products = ArrayList(
            Arrays.asList(
                    Product(0, "Bread", R.drawable.bread,
                            true),
                    Product(1, "Banana", R.drawable.banana,
                            true),
                    Product(2, "Sausage", R.drawable.sausage,
                    true),
                    Product(2, "Sausage", R.drawable.sausage,
                            true),
                    Product(2, "Sausage", R.drawable.sausage,
                            true),
                    Product(2, "Sausage", R.drawable.sausage,
                            true),
                    Product(2, "Sausage", R.drawable.sausage,
                            true)

            ))

    private val testRecipe = Recipe(0, "Сэндвич", "тест", R.drawable.shutterstock_525130276, null, 4,
            "description",
            false)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val gridView = findViewById<View>(R.id.gridview) as GridView
        val productsAdapter = ProductsAdapter(this, products)
        gridView.adapter = productsAdapter
    }

    fun openRecipe(view: View) {
        val recipeIntent = Intent(this, RecipeActivity::class.java)

        startActivity(recipeIntent)
    }

}


