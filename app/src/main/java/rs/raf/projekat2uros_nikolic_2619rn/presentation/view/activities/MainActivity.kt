package rs.raf.projekat2uros_nikolic_2619rn.presentation.view.activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.fragment.app.FragmentTransaction
import rs.raf.projekat2uros_nikolic_2619rn.R
import rs.raf.projekat2uros_nikolic_2619rn.databinding.ActivityMainBinding
import rs.raf.projekat2uros_nikolic_2619rn.presentation.view.fragments.GlavniFragment

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        init()
    }

    private fun init() {
        val transaction: FragmentTransaction = supportFragmentManager.beginTransaction()
        transaction.addToBackStack(null)
        transaction.replace(R.id.mainFragmentFcv, GlavniFragment())
        transaction.commit()
    }

}
