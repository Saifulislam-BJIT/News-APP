package com.saiful.newsapp.ui

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.saiful.newsapp.R
import com.saiful.newsapp.databinding.ActivityMainBinding
import com.saiful.newsapp.ui.fragment.BookmarkFragment
import com.saiful.newsapp.ui.fragment.HomeFragment

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

//        bottom navigation
        binding.bottomNavigation.setOnItemSelectedListener {
            when(it.itemId) {
                R.id.action_bookmark -> loadFragment(BookmarkFragment())
                else -> loadFragment(HomeFragment())
            }
            true
        }
    }

    private fun loadFragment(fragment: Fragment) {
        val transaction = supportFragmentManager.beginTransaction()
        transaction.replace(R.id.nav_host_fragment, fragment)
        transaction.commit()
    }
}