package com.buildforsdg.openmarket.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.navigateUp
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import com.buildforsdg.openmarket.R
import com.buildforsdg.openmarket.extension.makeGone
import com.buildforsdg.openmarket.extension.makeVisible
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    private lateinit var appBarConfiguration: AppBarConfiguration
    private val navController by lazy { findNavController(R.id.nav_host_fragment)}

    override fun onCreate(savedInstanceState: Bundle?) {
        setTheme(R.style.AppTheme_NoActionBar)
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setSupportActionBar(toolbar)

        appBarConfiguration = AppBarConfiguration(
            setOf(
                R.id.homeFragment,R.id.exploreFragment,
                R.id.cartFragment,R.id.offerFragment,
                R.id.profileFragment
            ), null
        )
        bottomNavView.setupWithNavController(navController)
        setupActionBarWithNavController(navController, appBarConfiguration)
        toolbarVisibilityManager()
    }

    override fun onSupportNavigateUp(): Boolean {
        return navController.navigateUp(appBarConfiguration) || super.onSupportNavigateUp()
    }

    private fun toolbarVisibilityManager() {
        navController.addOnDestinationChangedListener { _, destination, _ ->
            when (destination.id) {
                R.id.loginFragment, R.id.registerFragment -> {
                    toolbar.makeGone()
                    bottomNavView.makeGone()
                }
                R.id.homeFragment,R.id.exploreFragment,
                R.id.cartFragment,R.id.offerFragment,
                R.id.profileFragment -> {
                    toolbar.makeGone()
                    bottomNavView.makeVisible()
                }
                else -> {
                    toolbar.makeVisible()
                    bottomNavView.makeGone()
                }
            }
        }
    }
}