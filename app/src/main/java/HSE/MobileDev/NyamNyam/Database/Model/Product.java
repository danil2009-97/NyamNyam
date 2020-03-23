package HSE.MobileDev.NyamNyam.Database.Model;

public class Product {


    private int id;
    private String name;
    private String imageSource;
    private int isAvailable;


    public Product() {
    }

    public Product(int id, String name, String imageSource, int isAvailable ) {
        this.id = id;
        this.name = name;
        this.imageSource = imageSource;
        this.isAvailable = isAvailable;
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

    public String getImageSource() {
        return imageSource;
    }

    public void setImageSource(String imageSource) {
        this.imageSource = imageSource;
    }

    public int getIsAvailable() {
        return isAvailable;
    }

    public void setIsAvailable(int isAvailable) {this.isAvailable = isAvailable; }
}
