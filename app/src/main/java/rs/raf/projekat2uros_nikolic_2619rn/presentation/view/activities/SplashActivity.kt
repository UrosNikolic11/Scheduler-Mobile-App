package rs.raf.projekat2uros_nikolic_2619rn.presentation.view.activities

import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import org.koin.android.ext.android.inject

class SplashActivity: AppCompatActivity() {

    val userKey = "userKey"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        init()
    }

    private fun init(){
        val pref: SharedPreferences by inject()
        val username: String? = pref.getString(userKey,null)
        val splash: androidx.core.splashscreen.SplashScreen = installSplashScreen()
        splash.setKeepOnScreenCondition {
            println(username + " AKAKAKAKAK")
            if(username.equals(null)){
                intent = Intent(this,LoginActivity::class.java)
                startActivity(intent)
                finish()
            }
            else{
                intent = Intent(this,MainActivity::class.java)
                startActivity(intent)
                finish()
            }
            false
        }
    }
}