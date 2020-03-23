package HSE.MobileDev.NyamNyam

import android.os.Bundle
import android.widget.ImageView
import android.widget.TextView

import androidx.appcompat.app.AppCompatActivity

import HSE.MobileDev.NyamNyam.Database.Model.Recipe
import android.content.res.Configuration
import android.os.PersistableBundle
import android.view.View
import java.util.*
import kotlin.collections.ArrayList

class RecipeActivity : AppCompatActivity() {

    private var currentIndex:Int = -1;
    private var listSize:Int = -1;
    private var recipeList = ArrayList<Recipe>()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val lastInd = savedInstanceState?.getInt("Index")

        if (lastInd != null) {
            currentIndex = lastInd
        } else {
            currentIndex = -1
        }

        getRecipeList()
        setContentView(R.layout.constraintlayout_recipe)
        val initialRecipe:Recipe = getInitialRecipe()
        fillWindow(initialRecipe)
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        outState.putInt("Index", currentIndex)
    }

    override fun onRestoreInstanceState(savedInstanceState: Bundle) {
        super.onRestoreInstanceState(savedInstanceState)
        currentIndex = savedInstanceState.getInt("Index")
    }

    private fun getInitialRecipe () : Recipe{
        val emptyRecipe = Recipe(-1, "Нет доступных рецептов!", "Упс...",
                R.drawable.sad, null, 0,"Добавьте продукты в Ваш холодильник", false)

        if (listSize > 0){
            if (currentIndex>-1){
                return recipeList[currentIndex]
            }
            else {
                currentIndex = 0
                return recipeList[currentIndex]
            }
        }
        else{
            return emptyRecipe
        }
    }

    private fun getRecipeList(){
        // request from db
        recipeList.addAll(Arrays.asList(
                Recipe(0, "Сэндвич", "тест", R.drawable.shutterstock_525130276, null, 4,
                        "Long sample text to test scrollbar. Long sample text to test scrollbar. Long sample text to test scrollbar. Long sample text to test scrollbar. Long sample text to test scrollbar. Long sample text to test scrollbar. Long sample text to test scrollbar. Long sample text to test scrollbar. Long sample text to test scrollbar. Long sample text to test scrollbar. Long sample text to test scrollbar. Long sample text to test scrollbar. Long sample text to test scrollbar. Long sample text to test scrollbar.",
                        false),
                Recipe(1, "Второй сэндвич с длинным названием", "тест", R.drawable.sausage, null, 3,
                        "quite short text", false)
        ))

        listSize = recipeList.size

    }


    protected fun fillWindow(recipe:Recipe) {
        val productTitleTextView = findViewById<View>(R.id.productTitleTextView) as TextView
        val difficultyTitleTextView = findViewById<View>(R.id.difficultyTextView) as TextView
        val mealImageView = findViewById<View>(R.id.mealImageView) as ImageView
        val recipeTextView = findViewById<View>(R.id.recipeTextView) as TextView

        val difficulty = String.format("Сложность: %1\$d/5", recipe.difficulty)

        mealImageView.setImageResource(recipe.imageSource)
        productTitleTextView.text = recipe.name
        difficultyTitleTextView.setText(difficulty)
        recipeTextView.text = recipe.guide
    }

    fun onNextClick(view: View){
        if(currentIndex!=-1 && currentIndex<listSize-1){
            currentIndex++
            fillWindow(recipeList[currentIndex])
        }
    }

    fun onPreviousClick(view: View){
        if(currentIndex>0){
            currentIndex--
            fillWindow(recipeList[currentIndex])
        }
    }

    fun closeActivity(view: View) {
        super.onBackPressed()
    }
}
