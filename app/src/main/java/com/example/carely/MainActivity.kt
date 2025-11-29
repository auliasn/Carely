package com.example.carely

import android.content.res.ColorStateList
import android.graphics.Color
import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.navigation.findNavController
import androidx.navigation.ui.setupWithNavController
import com.google.android.material.bottomnavigation.BottomNavigationView

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        val navController = findNavController(R.id.fragmentContainerView)
        val bottomNav = findViewById<BottomNavigationView>(R.id.bottomNavigationMenu)
        bottomNav.setupWithNavController(navController)

        val username = intent.getStringExtra(LoginActivity.KEY_USERNAME)
        navController.addOnDestinationChangedListener { controller, destination, _ ->
            if (destination.id == R.id.profileFragment) {
                val bundle = Bundle().apply {
                    putString("username", username)
                }

                if (destination.id != controller.currentDestination?.id) {
                    controller.navigate(R.id.profileFragment, bundle)
                }
            }
        }

        val states = arrayOf(
            intArrayOf(android.R.attr.state_checked),
            intArrayOf(-android.R.attr.state_checked)
        )

        val colors = intArrayOf(
            Color.BLACK,
            Color.WHITE
        )

        val colorStateList = ColorStateList(states, colors)
        bottomNav.itemIconTintList = colorStateList
        bottomNav.itemTextColor = colorStateList
    }
}
