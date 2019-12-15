package HSE.MobileDev.NyamNyam.Views;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import HSE.MobileDev.NyamNyam.Database.Model.Product;
import HSE.MobileDev.NyamNyam.R;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    private Product[] products = {
            new Product(1,1,"https://c7.hotpng.com/preview/575/426/540/cooking-banana-clip-art-banana.jpg",
                    true),
            new Product(2,2,"https://c7.hotpng.com/preview/322/96/938/thuringian-sausage-bratwurst-liverwurst-pelmeni-sausage-thumbnail.jpg",
                    true),
            new Product(3,3,"https://img2.freepng.ru/20181123/rx/kisspng-graham-bread-sourdough-bakery-small-bread-5bf8277eaad716.3975945715429896946998.jpg",
                    true)
    };
    //Test - GitCommit
}
