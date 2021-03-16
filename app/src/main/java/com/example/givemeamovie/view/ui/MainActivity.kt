package com.example.givemeamovie.view.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.databinding.DataBindingUtil
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import com.example.givemeamovie.R
import com.example.givemeamovie.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding: ActivityMainBinding = DataBindingUtil.setContentView(
                this, R.layout.activity_main)

        binding.lifecycleOwner = this

        val navController = findNavController(R.id.fragment)
        val appBarConfiguration = AppBarConfiguration(setOf(R.id.homeFragment, R.id.exploreFragment, R.id.favoriteFragment))

        setupActionBarWithNavController(navController, appBarConfiguration)

        binding.bottomNavigationView.setupWithNavController(navController)
    }
}