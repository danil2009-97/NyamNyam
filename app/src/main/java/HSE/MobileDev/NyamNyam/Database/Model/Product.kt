package HSE.MobileDev.NyamNyam.Database.Model

class Product {
    var id = 0
    var name: String? = null
    var imageSource: String? = null
    var isAvailable = 0

    constructor() {}
    constructor(id: Int, name: String?, imageSource: String?, isAvailable: Int) {
        this.id = id
        this.name = name
        this.imageSource = imageSource
        this.isAvailable = isAvailable
    }

}