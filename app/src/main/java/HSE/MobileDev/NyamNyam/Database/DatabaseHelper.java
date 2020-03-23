package HSE.MobileDev.NyamNyam.Database;


import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;
import java.util.List;

import HSE.MobileDev.NyamNyam.Database.Model.Product;
import HSE.MobileDev.NyamNyam.Database.Model.Recipe;


public class DatabaseHelper extends SQLiteOpenHelper {

    // Database Version
    private static final int DATABASE_VERSION = 1;

    // Database Name
    private static final String DATABASE_NAME = "fridge_db";

    //Table Names
    public static final String TABLE_PRODUCT = "products";
    public static final String TABLE_RECIPE = "recipes";
    public static final String TABLE_PRODUCT_RECIPE = "product_recipes";

    //Product table column names
    private static final String COLUMN_PRODUCT_ID = "product_id";
    private static final String COLUMN_PRODUCT_NAME = "product_name";
    private static final String COLUMN_PRODUCT_IMAGESOURCE = "product_imageSource";
    private static final String COLUMN_ISAVAILABLE= "isAvailable";

    //Recipe table column names
    private static final String COLUMN_RECIPE_ID = "recipe_id";
    private static final String COLUMN_RECIPE_NAME = "recipe_name";
    private static final String COLUMN_RECIPE_IMAGESOURCE = "recipe_imageSource";
    private static final String COLUMN_DESCRIPTION = "description";
    private static final String COLUMN_DIFFICULTY= "difficulty";
    private static final String COLUMN_GUIDE= "guide";

    //Product_Recipe table column names
    private static final String COLUMN_PR_PRODUCT_ID = "pr_product_id";
    private static final String COLUMN_PR_RECIPE_ID = "pr_recipe_id";

    //Product create statement
    private static final String CREATE_TABLE_PRODUCT =
            "CREATE TABLE " + TABLE_PRODUCT + "("
                    + COLUMN_PRODUCT_ID + " INTEGER PRIMARY KEY AUTOINCREMENT,"
                    + COLUMN_PRODUCT_NAME + " TEXT,"
                    + COLUMN_PRODUCT_IMAGESOURCE + " TEXT,"
                    + COLUMN_ISAVAILABLE + " INTEGER,"
                    + ")";

    //Recipe create statement
    public static final String CREATE_TABLE_RECIPE =
            "CREATE TABLE " + TABLE_RECIPE + "("
                    + COLUMN_RECIPE_ID + " INTEGER PRIMARY KEY AUTOINCREMENT,"
                    + COLUMN_RECIPE_NAME + " TEXT,"
                    + COLUMN_RECIPE_IMAGESOURCE + " TEXT,"
                    + COLUMN_DESCRIPTION + " TEXT,"
                    + COLUMN_DIFFICULTY + " INTEGER,"
                    + COLUMN_GUIDE + " TEXT"
                    + ")";


    //Product-Recipe create statement
    private static final String CREATE_TABLE_PRODUCTRECIPE =
            "CREATE TABLE " + TABLE_PRODUCT_RECIPE + "("
                    + COLUMN_PR_PRODUCT_ID + " INTEGER,"
                    + COLUMN_PR_RECIPE_ID + " INTEGER,"
                    + " FOREIGN KEY ("+COLUMN_PR_PRODUCT_ID+") REFERENCES "
                    +TABLE_PRODUCT+"("+COLUMN_PRODUCT_ID+")," +
                    " FOREIGN KEY ("+COLUMN_PR_RECIPE_ID+") REFERENCES "
                    +TABLE_RECIPE+"("+COLUMN_RECIPE_ID+")"
                    + ")";

    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    // Creating Tables
    @Override
    public void onCreate(SQLiteDatabase db) {
        // create notes table
        db.execSQL(CREATE_TABLE_PRODUCT);
        db.execSQL(CREATE_TABLE_RECIPE);
        db.execSQL(CREATE_TABLE_PRODUCTRECIPE);
    }

    // Upgrading database
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // Drop older table if existed
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_RECIPE);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_PRODUCT);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_PRODUCT_RECIPE);
        // Create tables again
        onCreate(db);
    }

    // PRODUCT METHODS
    public long createProduct(Product product, long[] products_ids) {
        // get writable database as we want to write data
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        // `id` and  will be inserted automatically.
        // no need to add them
        values.put(COLUMN_PRODUCT_NAME,product.getName());
        values.put(COLUMN_PRODUCT_IMAGESOURCE, product.getImageSource());
        values.put(COLUMN_ISAVAILABLE, product.getIsAvailable());
            // insert row
        long id = db.insert(TABLE_PRODUCT, null, values);
        //ТУТ МНОГО КО МНОГИМ
        for (long tag_id : tag_ids) {
            createTodoTag(todo_id, tag_id);
        }
        // close db connection
        db.close();
        // return newly inserted row id
        return id;
    }

    //GET 1 PRODUCT
    public Product getProduct(long id) {
        // get readable database as we are not inserting anything
        SQLiteDatabase db = this.getReadableDatabase();
        String selectQuery = "SELECT  * FROM " + TABLE_PRODUCT + " WHERE "
                + COLUMN_PRODUCT_ID + " = " + id;

        Cursor cursor = db.rawQuery(selectQuery, null);

        if (cursor != null)
            cursor.moveToFirst();
        // prepare note object
        Product product = new Product(
                cursor.getInt(cursor.getColumnIndex(COLUMN_PRODUCT_ID)),
                cursor.getString(cursor.getColumnIndex(COLUMN_PRODUCT_NAME)),
                cursor.getString(cursor.getColumnIndex(COLUMN_PRODUCT_IMAGESOURCE)),
                cursor.getInt(cursor.getColumnIndex(COLUMN_ISAVAILABLE)));
                // close the db connection
        cursor.close();
        return product;
    }

    public List<Product> getAllProducts() {
        List<Product> products = new ArrayList<>();

        // Select All Query
        String selectQuery = "SELECT  * FROM " + TABLE_PRODUCT;

        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);

        // looping through all rows and adding to list
        if (cursor.moveToFirst()) {
            do {
                Product product = new Product();
                product.setId(cursor.getInt(cursor.getColumnIndex(COLUMN_PRODUCT_ID)));
                product.setName(cursor.getString(cursor.getColumnIndex(COLUMN_PRODUCT_NAME)));
                product.setImageSource(cursor.getString(cursor.getColumnIndex(COLUMN_PRODUCT_IMAGESOURCE)));
                product.setIsAvailable(cursor.getInt(cursor.getColumnIndex(COLUMN_ISAVAILABLE)));

                products.add(product);
            } while (cursor.moveToNext());
        }
        // close db connection
        db.close();
        // return notes list
        return products;
    }

    public int updateNote(Note note) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(Note.COLUMN_NOTE, note.getNote());

        // updating row
        return db.update(Note.TABLE_NAME, values, Note.COLUMN_ID + " = ?",
                new String[]{String.valueOf(note.getId())});
    }

    public void deleteNote(Note note) {
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(Note.TABLE_NAME, Note.COLUMN_ID + " = ?",
                new String[]{String.valueOf(note.getId())});
        db.close();
    }
}
