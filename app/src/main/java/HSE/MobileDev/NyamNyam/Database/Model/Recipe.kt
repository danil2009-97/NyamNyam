package HSE.MobileDev.NyamNyam.Database.Model

class Recipe {
    var id = 0
    var name: String? = null
    var imageSource: String? = null
    var description: String? = null
    var difficulty = 0
    //private var products: List<Product>? = null

    //    public List<Product> getProducts() {return products; }
    //
    //    public void setProducts(List<Product> products) { this.products = products; }
    var guide: String? = null

   // constructor() {}
   // constructor(id: Int, name: String?, imageSource: String?, description: String?, difficulty: Int,
  //              products: List<Product>?, guide: String?) {
   constructor() {}
    constructor(id: Int, name: String?, imageSource: String?, description: String?, difficulty: Int,
                guide: String?) {
        this.id = id
        this.name = name
        this.imageSource = imageSource
        this.description = description
        this.difficulty = difficulty
        //this.products = products
        this.guide = guide
    }

}