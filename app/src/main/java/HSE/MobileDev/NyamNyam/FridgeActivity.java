package HSE.MobileDev.NyamNyam;

import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import HSE.MobileDev.NyamNyam.Database.Model.Recipe;

public class FridgeActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.constraintlayout_recipe);
        fillWindow();
    }

    protected void fillWindow(){
        final TextView productTitleTextView = (TextView)findViewById(R.id.productTitleTextView);
        final TextView difficultyTitleTextView = (TextView)findViewById(R.id.difficultyTextView);
        final ImageView mealImageView = (ImageView)findViewById(R.id.mealImageView);
        final TextView recipeTextView = (TextView)findViewById(R.id.recipeTextView);

        String difficulty = String.format("Сложность: %1$d/5",testRecipe.getDifficulty());

        mealImageView.setImageResource(testRecipe.getImageSource());
        productTitleTextView.setText(testRecipe.getName());
        difficultyTitleTextView.setText(difficulty);
        recipeTextView.setText(testRecipe.getGuide());
    }

    private Recipe testRecipe = new Recipe(0,"Сэндвич","тест",R.drawable.shutterstock_525130276, null,4,
            "Long sample text to test scrollbar. Long sample text to test scrollbar. Long sample text to test scrollbar. Long sample text to test scrollbar. Long sample text to test scrollbar. Long sample text to test scrollbar. Long sample text to test scrollbar. Long sample text to test scrollbar. Long sample text to test scrollbar. Long sample text to test scrollbar. Long sample text to test scrollbar. Long sample text to test scrollbar. Long sample text to test scrollbar. Long sample text to test scrollbar.",
            false);

}
