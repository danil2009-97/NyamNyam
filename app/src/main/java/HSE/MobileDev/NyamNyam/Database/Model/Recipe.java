package HSE.MobileDev.NyamNyam.Database.Model;

import java.util.List;

public class Recipe {

    private int id;
    private String name;
    private String description;
    private String imageSource;
    private boolean isAvailable;
    private List<Product> products;


    public Recipe() {
    }

    public Recipe(int id, String name, String description, String imageSource, boolean isAvailable, List<Product> products) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.imageSource = imageSource;
        this.isAvailable = isAvailable;
        this.products = products;
    }

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

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getImageSource() {
        return imageSource;
    }

    public void setImageSource(String imageSource) {
        this.imageSource = imageSource;
    }

    public boolean getAvailable() { return isAvailable; }

    public void setAvailable(boolean isAvailable) { this.isAvailable = isAvailable; }

    public List<Product> getProducts() { return products; }

    public void setProducts(List<Product> products) { this.products = products; }
}
