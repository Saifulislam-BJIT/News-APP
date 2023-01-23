package com.saiful.newsapp.ui

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.fragment.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import androidx.work.PeriodicWorkRequestBuilder
import androidx.work.WorkManager
import com.saiful.newsapp.R
import com.saiful.newsapp.databinding.ActivityMainBinding
import com.saiful.newsapp.global.Global
import com.saiful.newsapp.worker.NewsApiCallWorker
import java.util.concurrent.TimeUnit

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private lateinit var navController: NavController
    private lateinit var appBarConfiguration: AppBarConfiguration

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        Global.contextView = binding.navHostFragment

//        navigation
        val navHostFragment = supportFragmentManager
            .findFragmentById(R.id.nav_host_fragment) as NavHostFragment
        appBarConfiguration = AppBarConfiguration(
            setOf(R.id.homeFragment, R.id.bookmarkFragment)
        )
        navController = navHostFragment.findNavController()
        setupActionBarWithNavController(navController, appBarConfiguration)
        binding.bottomNavigation.setupWithNavController(navController)

        val work = PeriodicWorkRequestBuilder<NewsApiCallWorker>(3, TimeUnit.SECONDS)
            .build()
        WorkManager.getInstance(applicationContext).enqueue(work)
    }

    override fun onSupportNavigateUp(): Boolean {
        return navController.navigateUp() || super.onSupportNavigateUp()
    }
}