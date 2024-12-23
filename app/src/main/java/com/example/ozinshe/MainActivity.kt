package com.example.ozinshe

import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.NavController
import androidx.navigation.findNavController
import androidx.navigation.ui.setupWithNavController
import com.example.ozinshe.databinding.ActivityMainBinding
import com.example.ozinshe.domain.utils.NavigationHostProvider

class MainActivity : AppCompatActivity(), NavigationHostProvider {
    private var binding: ActivityMainBinding? = null
    private val navController: NavController by lazy {
        findNavController(R.id.nav_host_fragment)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding?.root)
        binding?.bottomNavigationBarMainActivity?.setOnNavigationItemSelectedListener {
            when (it.itemId) {
                R.id.homeFragment -> {
                    navController.navigate(R.id.homeFragment)
                }

                R.id.searchFragment -> {
                    navController.navigate(R.id.searchFragment)
                }

                R.id.bookmarkFragment -> {
                    navController.navigate(R.id.bookmarkFragment)
                }
                R.id.profileFragment -> {
                    navController.navigate(R.id.profileFragment)
                }
                else -> null
            } != null
        }
    }

    override fun setNavigationVisibility(visible: Boolean) {
        if (visible) {
            binding?.bottomNavigationBarMainActivity?.visibility = View.VISIBLE
        } else {
            binding?.bottomNavigationBarMainActivity?.visibility = View.GONE
        }
    }
}
