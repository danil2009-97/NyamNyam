package HSE.MobileDev.NyamNyam.Database.Model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
class Recipe
(
        @PrimaryKey
        @ColumnInfo(name = "id")
        var id: Int,

        @ColumnInfo(name = "name")
        var name: String?,

        @ColumnInfo(name = "description")
        var description: String?,

        @ColumnInfo(name = "imageSource")
        val imageSource: Int,

        @ColumnInfo(name = "products")
        var products: List<Product>?,

        @ColumnInfo(name = "difficulty")
        var difficulty: Int,

        @ColumnInfo(name = "guide")
        var guide: String?,

        @ColumnInfo(name = "favourite")
        var favourite: Boolean
)
