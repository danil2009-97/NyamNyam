package HSE.MobileDev.NyamNyam

import android.app.Application
import android.content.Context
import android.content.res.Resources
import androidx.core.content.ContextCompat.getSystemService
import android.icu.lang.UCharacter.GraphemeClusterBreak.T



public class App : Application(){
    companion object{
        var AppInstance : App? = null

        fun getResource(): Resources? {

            return AppInstance?.resources
        }
    }


    override fun onCreate() {
        super.onCreate()
        AppInstance = this
    }

}