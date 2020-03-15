package HSE.MobileDev.NyamNyam.Database.Model

class Recipe(var id: Int, var name: String?, var description: String?, val imageSource: Int, var products: List<Product>?,
             var difficulty: Int, var guide: String?, var favourite: Boolean)
