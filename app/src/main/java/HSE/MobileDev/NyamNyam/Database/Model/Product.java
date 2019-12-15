package HSE.MobileDev.NyamNyam.Database.Model;

import java.util.List;

public class Product {

    private int id;
    private String name;
    private String description;
    private String imageSource;
    private int difficulty;
    private String guide;
    private boolean isFavourite;


    public Product() {
    }

    public Product(int id, String name, String description, String imageSource, int difficulty, String guide, boolean isFavourite) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.imageSource = imageSource;
        this.difficulty = difficulty;
        this.guide = guide;
        this.isFavourite = isFavourite;
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

    public int getDifficulty() {
        return difficulty;
    }

    public void setDifficulty(int difficulty) {
        this.difficulty = difficulty;
    }

    public String getGuide() {
        return guide;
    }

    public void setGuide(String guide) {
        this.guide = guide;
    }

    public boolean getFavourite() { return isFavourite; }

    public void setFavourite(boolean isFavourite) { this.isFavourite = isFavourite; }

}
