package HSE.MobileDev.NyamNyam.Database.Model

import android.app.Application
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = arrayOf(Product::class, Recipe::class), version = 1)
abstract class AppDatabase : RoomDatabase()
{
    companion object {
        fun get(application: Application) : AppDatabase{
            return Room.databaseBuilder(application, AppDatabase::class.java,"Fridge").build()
        }
    }
    abstract fun getMyDao() : MyDao
}


