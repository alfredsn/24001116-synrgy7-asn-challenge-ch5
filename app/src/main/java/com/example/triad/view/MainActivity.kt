package com.example.triad.view

import android.os.Bundle
import androidx.activity.OnBackPressedCallback
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.NavHostFragment
import com.example.triad.R
import com.example.triad.datastore.DataStoreManager
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.launch

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val navHostFragment = supportFragmentManager.findFragmentById(R.id.nav_host_fragment_container) as NavHostFragment
        val navController = navHostFragment.navController

        lifecycleScope.launch {
            val isLoggedIn = DataStoreManager.getLoginStatus(this@MainActivity).first()
            val navGraph = navController.navInflater.inflate(R.navigation.navigation_main)
            navGraph.setStartDestination(if (isLoggedIn) R.id.mainFragment else R.id.loginFragment)
            navController.graph = navGraph
        }

        onBackPressedDispatcher.addCallback(this, object : OnBackPressedCallback(true) {
            override fun handleOnBackPressed() {
                if (navController.currentDestination?.id == R.id.loginFragment) {
                    finish()
                } else {
                    navController.navigateUp()
                }
            }
        })
    }
}
