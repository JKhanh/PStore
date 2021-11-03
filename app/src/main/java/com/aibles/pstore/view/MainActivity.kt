package com.aibles.pstore.view

import activityViewBinding
import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.databinding.DataBindingUtil
import androidx.navigation.NavHostController
import androidx.navigation.findNavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.NavigationUI
import com.aibles.pstore.R
import com.aibles.pstore.databinding.ActivityMainBinding
import com.aibles.pstore.databinding.ItemProductBinding
import dagger.hilt.android.AndroidEntryPoint
import np.com.susanthapa.curved_bottom_navigation.CbnMenuItem

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {
    private val binding by activityViewBinding(ActivityMainBinding::inflate)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        setupNavigation()
    }
    
    private fun setupNavigation() {
        val navHostFragment = supportFragmentManager.findFragmentById(R.id.nav_host_fragment_container) as NavHostFragment
        val menuItems = arrayOf(
            CbnMenuItem(
                R.drawable.ic_baseline_home_24,
                R.drawable.adv_home,
                R.id.homeFragment
            ),
            CbnMenuItem(
                R.drawable.ic_outline_shopping_cart_24,
                R.drawable.adv_cart,
                R.id.cartFragment
            ),
            CbnMenuItem(
                R.drawable.ic_baseline_person_24,
                R.drawable.adv_profile,
                R.id.accountFragment
            ),
        )

        binding.bottomNav.setMenuItems(menuItems, 0)
        binding.bottomNav.setupWithNavController(navHostFragment.navController)
    }

    override fun onSupportNavigateUp(): Boolean {
        return findNavController(R.id.nav_host_fragment_container).navigateUp()
    }
}