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
                    new Product(0,"Bread","https://c7.hotpng.com/preview/575/426/540/cooking-banana-clip-art-banana.jpg",
                    true),
                    new Product(1,"Banana","https://c7.hotpng.com/preview/322/96/938/thuringian-sausage-bratwurst-liverwurst-pelmeni-sausage-thumbnail.jpg",
                    true),
                    new Product(2,"Smthng","https://img2.freepng.ru/20181123/rx/kisspng-graham-bread-sourdough-bakery-small-bread-5bf8277eaad716.3975945715429896946998.jpg",
                    true)
                ));




//            new Product(1,1,"https://c7.hotpng.com/preview/575/426/540/cooking-banana-clip-art-banana.jpg",
//                    true),
//            new Product(2,2,"https://c7.hotpng.com/preview/322/96/938/thuringian-sausage-bratwurst-liverwurst-pelmeni-sausage-thumbnail.jpg",
//                    true),
//            new Product(3,3,"https://img2.freepng.ru/20181123/rx/kisspng-graham-bread-sourdough-bakery-small-bread-5bf8277eaad716.3975945715429896946998.jpg",
//                    true);

    //Test - GitCommit
}
