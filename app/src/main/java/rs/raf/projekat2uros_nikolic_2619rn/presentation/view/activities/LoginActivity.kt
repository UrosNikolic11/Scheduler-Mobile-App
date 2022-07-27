package rs.raf.projekat2uros_nikolic_2619rn.presentation.view.activities

import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.ui.platform.ComposeView
import org.koin.android.ext.android.inject
import rs.raf.projekat2uros_nikolic_2619rn.R
import rs.raf.projekat2uros_nikolic_2619rn.presentation.view.composable.LoginLayout

class LoginActivity: AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)
        val composeHolder = findViewById<ComposeView>(R.id.composeHolder)
        composeHolder.setContent {
            LoginLayout(onClick = ::login)
        }
    }

    private fun login(user: String, pin: String){
        if(user != "" && pin != ""){
            val pref: SharedPreferences by inject()
            pref.edit().putString("userKey", user).apply()
            intent = Intent(this,MainActivity::class.java)
            startActivity(intent)
            finish()
        }
        else Toast.makeText(this, "Niste uneli sve podatke", Toast.LENGTH_SHORT).show()
    }
}