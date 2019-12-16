package HSE.MobileDev.NyamNyam.Database.Model;

import java.util.List;

public class Product {
    private int id;
    private String name;
    private final int imageResource;
    private boolean isAvailable;


    public Product(int id, String name,int imageResource,boolean isAvailable ) {
        this.id = id;
        this.name = name;
        this.imageResource = imageResource;
        this.isAvailable = isAvailable;
    }

    public boolean getAvailable() { return isAvailable; }

    public void setAvailable(boolean isAvailable) { this.isAvailable = isAvailable; }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getImageResource() {
        return imageResource;
    }

   /* public void setImageSource(String imageSource) {
        this.imageSource = imageSource;
    }*/


}
