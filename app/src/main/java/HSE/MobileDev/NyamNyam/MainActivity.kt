package HSE.MobileDev.NyamNyam

import HSE.MobileDev.NyamNyam.Database.DatabaseHelper
import HSE.MobileDev.NyamNyam.Database.Model.Product
import HSE.MobileDev.NyamNyam.Database.Model.Recipe
import HSE.MobileDev.NyamNyam.Views.ProductsAdapter
import android.content.Intent
import android.os.Bundle
import android.provider.ContactsContract.CommonDataKinds.Note
import android.view.View
import android.widget.AdapterView.OnItemClickListener
import android.widget.GridView
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import java.util.*
import androidx.core.app.ComponentActivity
import androidx.core.app.ComponentActivity.ExtraData
import androidx.core.content.ContextCompat.getSystemService
import android.icu.lang.UCharacter.GraphemeClusterBreak.T




class MainActivity : AppCompatActivity() {

    private var productsAdapter : ProductsAdapter? = null
    private var gridView : GridView? = null

    var productList: MutableList<Product> = ArrayList()
    private var db: DatabaseHelper? = null




    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        db = DatabaseHelper(this)

        var fromDb = db!!.allProductsAvailable
        var fromDb2 = db!!.allProducts

        if (fromDb.size > 0 || fromDb2.size >0){
            productList.addAll(fromDb)
        }
        else{
            fillDb()
        }



        gridView = findViewById<View>(R.id.gridview) as GridView
        productsAdapter = ProductsAdapter(this, productList)
        gridView!!.adapter = productsAdapter

        gridView!!.onItemClickListener = OnItemClickListener { parent, view, position, id ->
            // Get the GridView selected/clicked item text
            showActionsDialog(position)
        };
    }

    fun fillDb(){
        val recipe1 = Recipe(1,"Бутерброд с маслом и колбасой","buterbrod", "очень вкусное", 1, "Подготовьте продукты по списку. Помидоры нарезаем кружками или дольками. Выкладываем их на ломти тостового хлеба. Закрываем помидоры сыром - выкладываем по два сырных ломтика на каждый бутерброд. Добавляем свежую зелень. При желании солим и перчим. Прикрываем оставшимися ломтиками хлеба. Подсушиваем заготовки на раскаленной сковороде по 2-3 минуты с каждой стороны, до плавления сыра и легкой золотистой корочки хлеба. Можно без крышки.");
        val recipe_1 = db!!.createRecipe(recipe1);

        val recipe2 = Recipe(2,"Гречка с молоком","grechka", "не вкуснее чем бутерброд", 1, "Подготовьте молоко, гречневую крупу и питьевую воду в соотношении 1:1:1, то есть, на стакан гречки вам понадобится по стакану молока и воды. Соль и сахар не обязательны, но вы можете использовать их по желанию, определив большее или меньшее количество на свой вкус. Переберите гречку, тщательно выбирая и удаляя не только соринки и примеси, но и потемневшие ядра. Налейте воду в небольшую неэмалированную кастрюлю или ковшик с толстым дном и доведите до кипения. Когда вода закипит, засыпьте в нее крупу и убавьте огонь до минимума.");
        val recipe_2 = db!!.createRecipe(recipe2);

        val recipe3 = Recipe(3,"Омлет","omlet", "вкусный омлет на завтрак", 2, "Разбить яйца в миску, взболтать их вилкой, влить молоко, перемешать, посолить. Подогреть сковороду с маслом (можно и без, если жарите на тефлоне), влить яйца и жарить минут 5 на медленном огне. При желании можно перемешать омлет, чтобы он поджарился равномерно. Или накрыть его крышкой и подержать минуты 2-3 - так он станет пышным.");
        val recipe_3 = db!!.createRecipe(recipe3);

        val recipe4 = Recipe(4,"Царский бутербод","royalbuter", "королевский бутер", 3, "Отрезать от хлебa 6 кусков. Помидор порезать так же на 6 частей, а огурец на 12 (чтобы обе стороны круга помидора и огурца должны быть обрезанные). От колбасы отрезать так же 6 кусочков, толщиной примерно по 0,5 см. Хлеб и колбасу поджарить на масле до румянца. На хлеб положить салат, на салат 2 огурчика, на огурчик колбасу, на колбасу помидор. Можно вырезать фигурки и вставить длинные шпажки. ");
        val recipe_4 = db!!.createRecipe(recipe4);


        val product1 = Product(1, "Хлеб", "bread", 1);
        val product_1: Long = db!!.createProduct(product1, longArrayOf(1, 4))

        val product2 = Product(2, "Масло", "butter", 1);
        val product_2: Long = db!!.createProduct(product2, longArrayOf(1, 4))

        val product3 = Product(3, "Колбаса", "sausage", 1);
        val product_3: Long = db!!.createProduct(product3, longArrayOf(1, 3, 4))

        val product4 = Product(4, "Гречка", "buckwheat", 1);
        val product_4: Long = db!!.createProduct(product4, longArrayOf(2))

        val product5 = Product(5, "Молоко", "milk", 1);
        val product_5: Long = db!!.createProduct(product5, longArrayOf(2,3))

        val product6 = Product(6, "Яйцо", "egg", 1);
        val product_6: Long = db!!.createProduct(product6, longArrayOf(3))
    }

    private fun showActionsDialog(position: Int) {
        val colors = arrayOf<CharSequence>("Да", "Нет")

        val builder = AlertDialog.Builder(this)
        builder.setTitle("Удалить продукт?")
        builder.setItems(colors) { dialog, which ->
            if (which == 0) {
                deleteProductFromFridge(position)
                productsAdapter = ProductsAdapter(this, productList)
                gridView!!.adapter = productsAdapter
            }
        }
        builder.show()
    }

    private fun deleteProductFromFridge(position: Int){

        val p: Product = productList[position]
        p.isAvailable = 0
        db!!.updateProduct(p)
        // по инту входному получаю индекс в массиве, по нему беру его id
        //по id проапдейтить available = 0
        productList.removeAt(position)
    }


    public override fun onResume() {
        productList = ArrayList()
        productList.addAll(db!!.allProductsAvailable)
        productsAdapter = ProductsAdapter(this, productList)
        gridView!!.adapter = productsAdapter

        super.onResume()

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


