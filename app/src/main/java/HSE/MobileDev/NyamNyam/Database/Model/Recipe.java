package HSE.MobileDev.NyamNyam.Database.Model;

import java.util.List;

public class Recipe {

    private int id;
    private String name;
    private String description;
    private final int imageSource;

    private List<Product> products;
    private int difficulty;
    private String guide;
    private boolean isFavourite;

    public Recipe(int id, String name, String description, int imageSource,  List<Product> products,
                  int difficulty, String guide, boolean isFavourite) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.imageSource = imageSource;
        this.difficulty = difficulty;
        this.guide = guide;
        this.isFavourite = isFavourite;
        this.products = products;
    }

    public String getGuide() {
        return guide;
    }

    public void setGuide(String guide) {
        this.guide = guide;
    }

    public boolean getFavourite() { return isFavourite; }

    public void setFavourite(boolean isFavourite) { this.isFavourite = isFavourite; }

    public int getDifficulty() {
        return difficulty;
    }

    public void setDifficulty(int difficulty) {
        this.difficulty = difficulty;}

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

    public int getImageSource() {
        return imageSource;
    }

    public List<Product> getProducts() { return products; }

    public void setProducts(List<Product> products) { this.products = products; }
}
