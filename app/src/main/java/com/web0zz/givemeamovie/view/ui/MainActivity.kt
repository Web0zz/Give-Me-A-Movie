package com.web0zz.givemeamovie.view.ui

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.web0zz.givemeamovie.R
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val navController = findNavController(R.id.frag_container)
        val appBarConfiguration = AppBarConfiguration(setOf(R.id.homeFragment, R.id.exploreFragment, R.id.favoriteFragment))

        setupActionBarWithNavController(navController, appBarConfiguration)

        val navBar: BottomNavigationView = findViewById(R.id.bottomNavigationView)
        navBar.setupWithNavController(navController)
    }
}
