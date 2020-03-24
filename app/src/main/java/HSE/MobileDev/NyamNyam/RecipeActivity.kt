package HSE.MobileDev.NyamNyam

import HSE.MobileDev.NyamNyam.Database.DatabaseHelper
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
import androidx.core.app.ComponentActivity
import androidx.core.app.ComponentActivity.ExtraData
import androidx.core.content.ContextCompat.getSystemService
import android.icu.lang.UCharacter.GraphemeClusterBreak.T



class RecipeActivity : AppCompatActivity() {

    private var currentIndex:Int = -1;
    private var listSize:Int = -1;
    private var recipeList = ArrayList<Recipe>()
    var db: DatabaseHelper? = null


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val lastInd = savedInstanceState?.getInt("Index")
        db = DatabaseHelper(this)

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
        val emptyRecipe = Recipe(-1, "Нет доступных рецептов!",
                "sad", "test", 0,"Добавьте продукты в Ваш холодильник")

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
        // = запрос из базы трудный, который показывает рецепты, для которых есть все продукты (isav = 1)
        recipeList.addAll(db!!.allRecipes)

        listSize = recipeList.size

    }

    protected fun fillWindow(recipe:Recipe) {
        val productTitleTextView = findViewById<View>(R.id.productTitleTextView) as TextView
        val difficultyTitleTextView = findViewById<View>(R.id.difficultyTextView) as TextView
        val mealImageView = findViewById<View>(R.id.mealImageView) as ImageView
        val recipeTextView = findViewById<View>(R.id.recipeTextView) as TextView

        val difficulty = String.format("Сложность: %1\$d/5", recipe.difficulty)

        val uri = "@drawable/" + recipe.imageSource
        val imageSource = resources.getIdentifier(uri, null, packageName)
        mealImageView.setImageResource(imageSource)
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

    fun closeRecipeActivity(view: View) {
        super.onBackPressed()
    }
}
