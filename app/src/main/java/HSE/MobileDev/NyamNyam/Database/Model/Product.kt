package HSE.MobileDev.NyamNyam.Database.Model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
class Product
(
        @PrimaryKey
        @ColumnInfo(name = "id")
        var id: Int,

        @ColumnInfo(name = "name")
        var name: String?,

        @ColumnInfo(name = "imageResource")
        val imageResource: Int,

        @ColumnInfo(name = "available")
        var available: Boolean
)
