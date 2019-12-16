package HSE.MobileDev.NyamNyam.Views;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.GridView;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import HSE.MobileDev.NyamNyam.Database.Model.Product;
import HSE.MobileDev.NyamNyam.R;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        GridView gridView = (GridView)findViewById(R.id.gridview);
        final ProductsAdapter productsAdapter = new ProductsAdapter(this, products);
        gridView.setAdapter(productsAdapter);
    }

    private ArrayList<Product> products = new ArrayList<Product>(
            Arrays.asList(
                    new Product(0,"Bread",R.drawable.bread,
                    true),
                    new Product(1,"Banana",R.drawable.banana,
                    true),
                    new Product(2,"Sausage",R.drawable.sausage,
                    true)

                ));
}
