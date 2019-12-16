package HSE.MobileDev.NyamNyam.Database.Model;

import java.util.List;

public class Product {
    private int id;
    private String name;
    private String imageSource;
    private boolean isAvailable;

    public Product() {
    }

    public Product(int id, String name,String imageSource,boolean isAvailable ) {
        this.id = id;
        this.name = name;
        this.imageSource = imageSource;
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

    public String getImageSource() {
        return imageSource;
    }

    public void setImageSource(String imageSource) {
        this.imageSource = imageSource;
    }


}
