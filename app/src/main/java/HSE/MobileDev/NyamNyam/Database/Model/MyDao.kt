package HSE.MobileDev.NyamNyam.Database.Model

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface MyDao
{
    @Query("SELECT * FROM Product")
    fun getAll(): List<Product>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertProduct(list: List<Product>)
}