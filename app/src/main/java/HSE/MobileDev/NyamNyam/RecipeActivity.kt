package HSE.MobileDev.NyamNyam

import android.os.Bundle
import android.widget.ImageView
import android.widget.TextView

import androidx.appcompat.app.AppCompatActivity

import HSE.MobileDev.NyamNyam.Database.Model.Recipe
import android.view.View

class RecipeActivity : AppCompatActivity() {

    private val testRecipe = Recipe(0, "Сэндвич", "тест", R.drawable.shutterstock_525130276, null, 4,
            "Long sample text to test scrollbar. Long sample text to test scrollbar. Long sample text to test scrollbar. Long sample text to test scrollbar. Long sample text to test scrollbar. Long sample text to test scrollbar. Long sample text to test scrollbar. Long sample text to test scrollbar. Long sample text to test scrollbar. Long sample text to test scrollbar. Long sample text to test scrollbar. Long sample text to test scrollbar. Long sample text to test scrollbar. Long sample text to test scrollbar.",
            false)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.constraintlayout_recipe)
        fillWindow()
    }

    protected fun fillWindow() {
        val productTitleTextView = findViewById<View>(R.id.productTitleTextView) as TextView
        val difficultyTitleTextView = findViewById<View>(R.id.difficultyTextView) as TextView
        val mealImageView = findViewById<View>(R.id.mealImageView) as ImageView
        val recipeTextView = findViewById<View>(R.id.recipeTextView) as TextView

        val difficulty = String.format("Сложность: %1\$d/5", testRecipe.difficulty)

        mealImageView.setImageResource(testRecipe.imageSource)
        productTitleTextView.text = testRecipe.name
        difficultyTitleTextView.setText(difficulty)
        recipeTextView.text = testRecipe.guide
    }

    fun closeActivity(view: View) {
        super.onBackPressed()
    }
}
