package HSE.MobileDev.NyamNyam.Database

import HSE.MobileDev.NyamNyam.Database.Model.Product
import HSE.MobileDev.NyamNyam.Database.Model.Recipe
import android.content.ContentValues
import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import java.util.*
import kotlin.collections.ArrayList

class DatabaseHelper(context: Context?) : SQLiteOpenHelper(context, DATABASE_NAME, null, DATABASE_VERSION) {
    // Creating Tables
    override fun onCreate(db: SQLiteDatabase) {
        // create notes table
        db.execSQL(CREATE_TABLE_PRODUCT)
        db.execSQL(CREATE_TABLE_RECIPE)
        db.execSQL(CREATE_TABLE_PRODUCTRECIPE)
    }

    // Upgrading database
    override fun onUpgrade(db: SQLiteDatabase, oldVersion: Int, newVersion: Int) {
        // Drop older table if existed
        db.execSQL("DROP TABLE IF EXISTS $TABLE_RECIPE")
        db.execSQL("DROP TABLE IF EXISTS $TABLE_PRODUCT")
        db.execSQL("DROP TABLE IF EXISTS $TABLE_PRODUCT_RECIPE")
        // Create tables again
        onCreate(db)
    }

    // PRODUCT METHODS
    fun createProduct(product: Product, recipe_ids: LongArray): Long {
        // get writable database as we want to write data
        val db = this.writableDatabase
        val values = ContentValues()
        // `id` and  will be inserted automatically.
        // no need to add them
        values.put(COLUMN_PRODUCT_NAME, product.name)
        values.put(COLUMN_PRODUCT_IMAGESOURCE, product.imageSource)
        values.put(COLUMN_ISAVAILABLE, product.isAvailable)
        // insert row
        val id = db.insert(TABLE_PRODUCT, null, values)
        //ТУТ МНОГО КО МНОГИМ
        for (recipe_id in recipe_ids) {
            createProductRecipe(id, recipe_id)
        }
        // close db connection
        db.close()
        // return newly inserted row id
        return id
    }

    //GET 1 PRODUCT
    fun getProduct(id: Long): Product {
        // get readable database as we are not inserting anything
        val db = this.readableDatabase
        val selectQuery = ("SELECT  * FROM " + TABLE_PRODUCT + " WHERE "
                + COLUMN_PRODUCT_ID + " = " + id)
        val cursor = db.rawQuery(selectQuery, null)
        cursor?.moveToFirst()
        // prepare note object
        val product = Product(
                cursor!!.getInt(cursor.getColumnIndex(COLUMN_PRODUCT_ID)),
                cursor.getString(cursor.getColumnIndex(COLUMN_PRODUCT_NAME)),
                cursor.getString(cursor.getColumnIndex(COLUMN_PRODUCT_IMAGESOURCE)),
                cursor.getInt(cursor.getColumnIndex(COLUMN_ISAVAILABLE)))
        // close the db connection
        cursor.close()
        return product
    }

    // Select All Query
    val allProducts: ArrayList<Product>
        get() {
            val products: ArrayList<Product> = ArrayList()

            // Select All Query
            val selectQuery = "SELECT  * FROM $TABLE_PRODUCT WHERE $COLUMN_ISAVAILABLE = 1"
            val db = this.writableDatabase
            val cursor = db.rawQuery(selectQuery, null)

            // looping through all rows and adding to list
            if (cursor.moveToFirst()) {
                do {
                    val product = Product()
                    product.id = cursor.getInt(cursor.getColumnIndex(COLUMN_PRODUCT_ID))
                    product.name = cursor.getString(cursor.getColumnIndex(COLUMN_PRODUCT_NAME))
                    product.imageSource = cursor.getString(cursor.getColumnIndex(COLUMN_PRODUCT_IMAGESOURCE))
                    product.isAvailable = cursor.getInt(cursor.getColumnIndex(COLUMN_ISAVAILABLE))
                    products.add(product)
                } while (cursor.moveToNext())
            }
            // close db connection
            db.close()
            // return notes list
            return products
        }

    fun updateProduct(product: Product): Int {
        val db = this.writableDatabase
        val values = ContentValues()
        values.put(COLUMN_PRODUCT_NAME, product.name)
        values.put(COLUMN_PRODUCT_IMAGESOURCE, product.imageSource)
        values.put(COLUMN_ISAVAILABLE, product.isAvailable)

        // updating row
        return db.update(TABLE_PRODUCT, values, "$COLUMN_PRODUCT_ID = ?", arrayOf(product.id.toString()))
    }

    fun deleteProduct(product: Product) {
        val db = this.writableDatabase
        db.delete(TABLE_PRODUCT, "$COLUMN_PRODUCT_ID = ?", arrayOf(product.id.toString()))
        db.close()
    }

    fun createRecipe(recipe: Recipe): Long {
        val db = this.writableDatabase
        val values = ContentValues()
        values.put(COLUMN_RECIPE_NAME, recipe.name)
        values.put(COLUMN_RECIPE_IMAGESOURCE, recipe.imageSource)
        values.put(COLUMN_DIFFICULTY, recipe.difficulty)
        values.put(COLUMN_DESCRIPTION, recipe.description)
        values.put(COLUMN_GUIDE, recipe.guide)

        // insert row
        return db.insert(TABLE_RECIPE, null, values)
    }

    // Select All Query
    val allRecipes: List<Recipe>
        get() {
            val recipes: MutableList<Recipe> = ArrayList()

            // Select All Query
            val selectQuery = "SELECT  * FROM $TABLE_RECIPE"
            val db = this.writableDatabase
            val cursor = db.rawQuery(selectQuery, null)

            // looping through all rows and adding to list
            if (cursor.moveToFirst()) {
                do {
                    val recipe = Recipe()
                    recipe.id = cursor.getInt(cursor.getColumnIndex(COLUMN_RECIPE_ID))
                    recipe.name = cursor.getString(cursor.getColumnIndex(COLUMN_RECIPE_NAME))
                    recipe.imageSource = cursor.getString(cursor.getColumnIndex(COLUMN_RECIPE_IMAGESOURCE))
                    recipe.description = cursor.getString(cursor.getColumnIndex(COLUMN_DESCRIPTION))
                    recipe.difficulty = cursor.getInt(cursor.getColumnIndex(COLUMN_DIFFICULTY))
                    recipe.guide = cursor.getString(cursor.getColumnIndex(COLUMN_GUIDE))
                    recipes.add(recipe)
                } while (cursor.moveToNext())
            }
            // close db connection
            db.close()
            // return notes list
            return recipes
        }

    fun updateRecipe(recipe: Recipe): Int {
        val db = this.writableDatabase
        val values = ContentValues()
        values.put(COLUMN_RECIPE_NAME, recipe.name)
        values.put(COLUMN_RECIPE_IMAGESOURCE, recipe.imageSource)
        values.put(COLUMN_DESCRIPTION, recipe.description)
        values.put(COLUMN_DIFFICULTY, recipe.difficulty)
        values.put(COLUMN_GUIDE, recipe.guide)

        // updating row
        return db.update(TABLE_RECIPE, values, "$COLUMN_RECIPE_ID = ?", arrayOf(recipe.id.toString()))
    }

    fun createProductRecipe(product_id: Long, recipe_id: Long): Long {
        val db = this.writableDatabase
        val values = ContentValues()
        values.put(COLUMN_PRODUCT_ID, product_id)
        values.put(COLUMN_RECIPE_ID, recipe_id)
        // values.put(KEY_CREATED_AT, getDateTime());
        return db.insert(TABLE_PRODUCT_RECIPE, null, values)
    }

    companion object {
        // Database Version
        private const val DATABASE_VERSION = 1

        // Database Name
        private const val DATABASE_NAME = "fridge_db"

        //Table Names
        const val TABLE_PRODUCT = "products"
        const val TABLE_RECIPE = "recipes"
        const val TABLE_PRODUCT_RECIPE = "product_recipes"

        //Product table column names
        private const val COLUMN_PRODUCT_ID = "product_id"
        private const val COLUMN_PRODUCT_NAME = "product_name"
        private const val COLUMN_PRODUCT_IMAGESOURCE = "product_imageSource"
        private const val COLUMN_ISAVAILABLE = "isAvailable"

        //Recipe table column names
        private const val COLUMN_RECIPE_ID = "recipe_id"
        private const val COLUMN_RECIPE_NAME = "recipe_name"
        private const val COLUMN_RECIPE_IMAGESOURCE = "recipe_imageSource"
        private const val COLUMN_DESCRIPTION = "description"
        private const val COLUMN_DIFFICULTY = "difficulty"
        private const val COLUMN_GUIDE = "guide"

        //Product_Recipe table column names
        private const val COLUMN_PR_PRODUCT_ID = "pr_product_id"
        private const val COLUMN_PR_RECIPE_ID = "pr_recipe_id"

        //Product create statement
        private const val CREATE_TABLE_PRODUCT = ("CREATE TABLE " + TABLE_PRODUCT + "("
                + COLUMN_PRODUCT_ID + " INTEGER PRIMARY KEY AUTOINCREMENT,"
                + COLUMN_PRODUCT_NAME + " TEXT,"
                + COLUMN_PRODUCT_IMAGESOURCE + " TEXT,"
                + COLUMN_ISAVAILABLE + " INTEGER"
                + ")")

        //Recipe create statement
        const val CREATE_TABLE_RECIPE = ("CREATE TABLE " + TABLE_RECIPE + "("
                + COLUMN_RECIPE_ID + " INTEGER PRIMARY KEY AUTOINCREMENT,"
                + COLUMN_RECIPE_NAME + " TEXT,"
                + COLUMN_RECIPE_IMAGESOURCE + " TEXT,"
                + COLUMN_DESCRIPTION + " TEXT,"
                + COLUMN_DIFFICULTY + " INTEGER,"
                + COLUMN_GUIDE + " TEXT"
                + ")")

        //Product-Recipe create statement
        private const val CREATE_TABLE_PRODUCTRECIPE = ("CREATE TABLE " + TABLE_PRODUCT_RECIPE + "("
                + COLUMN_PR_PRODUCT_ID + " INTEGER,"
                + COLUMN_PR_RECIPE_ID + " INTEGER,"
                + " FOREIGN KEY (" + COLUMN_PR_PRODUCT_ID + ") REFERENCES "
                + TABLE_PRODUCT + "(" + COLUMN_PRODUCT_ID + ")," +
                " FOREIGN KEY (" + COLUMN_PR_RECIPE_ID + ") REFERENCES "
                + TABLE_RECIPE + "(" + COLUMN_RECIPE_ID + ")"
                + ")")
    }
}