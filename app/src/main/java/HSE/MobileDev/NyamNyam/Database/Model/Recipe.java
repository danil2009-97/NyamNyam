package HSE.MobileDev.NyamNyam.Database.Model;

import java.util.List;

public class Recipe {

    private int id;
    private String name;
    private String imageSource;
    private String description;
    private int difficulty;
    private List<Product> products;
    private String guide;


    public Recipe() {
    }

    public Recipe(int id, String name, String imageSource, String description, int difficulty,
                  List<Product> products, String guide) {
        this.id = id;
        this.name = name;
        this.imageSource = imageSource;
        this.description = description;
        this.difficulty = difficulty;
        this.products = products;
        this.guide = guide;
    }

    public int getId() { return id; }

    public void setId(int id) { this.id = id; }

    public String getName() { return name; }

    public void setName(String name) { this.name = name; }

    public String getImageSource() { return imageSource; }

    public void setImageSource(String imageSource) { this.imageSource = imageSource; }

    public int getDifficulty() { return difficulty; }

    public void setDifficulty(int difficulty) {this.difficulty = difficulty; }

//    public List<Product> getProducts() {return products; }
//
//    public void setProducts(List<Product> products) { this.products = products; }

    public String getGuide() { return guide; }

    public void setGuide(String guide) { this.guide = guide; }
}
