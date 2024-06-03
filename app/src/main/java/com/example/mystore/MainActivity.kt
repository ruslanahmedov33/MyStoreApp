package com.example.mystore

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.core.view.isVisible
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.NavigationUI
import com.example.mystore.databinding.ActivityMainBinding
import com.google.android.material.bottomnavigation.BottomNavigationView

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setup()
    }


    private fun setup(){
        with(binding){
            val navHostFragment=supportFragmentManager.findFragmentById(R.id.fragmentContainerView) as NavHostFragment
            val navController=navHostFragment.navController

            NavigationUI.setupWithNavController(bottomNavView,navController)

            navController.addOnDestinationChangedListener{_,destination,_->
                when(destination.id){
                    R.id.girisilkFragment->bottomNavView.visibility=View.GONE
                    R.id.loginFragment->bottomNavView.visibility=View.GONE
                    R.id.parolUnutdumFragment->bottomNavView.visibility=View.GONE
                    R.id.registrationFragment->bottomNavView.visibility=View.GONE
                    R.id.OTPFragment->bottomNavView.visibility=View.GONE
                    R.id.detailsFragment->bottomNavView.visibility=View.GONE
                    R.id.profilFragment->bottomNavView.visibility=View.GONE
                    R.id.categoryFragment->bottomNavView.visibility=View.GONE
                    else->{
                        if (!bottomNavView.isVisible){
                            bottomNavView.visibility=View.VISIBLE
                        }
                    }
                }

            }

        }
    }


}