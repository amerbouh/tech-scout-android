package com.team3990.techscouting

import android.bluetooth.*
import android.bluetooth.BluetoothGattService.SERVICE_TYPE_PRIMARY
import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import java.util.*
import android.bluetooth.BluetoothGattCharacteristic
import android.bluetooth.le.AdvertiseCallback
import android.bluetooth.le.AdvertiseData
import android.bluetooth.le.AdvertiseSettings
import android.os.ParcelUuid
import android.view.Menu
import androidx.navigation.NavController
import androidx.navigation.Navigation
import androidx.navigation.findNavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.navigateUp
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import kotlinx.android.synthetic.main.activity_main.*
import java.nio.charset.Charset

class MainActivity : AppCompatActivity() {

    /** Properties */

    private lateinit var appBarConfiguration: AppBarConfiguration

    /** Activity's lifecycle */

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // Get an instance of the Nav Controller..
        val navController = findNavController(R.id.navHostFragment)
        val topLevelDestinations = setOf(R.id.matchDataInputFragment, R.id.pitDataInputFragment, R.id.dataSheetListFragment)

        // Initialize the app bar configuration property.
        appBarConfiguration = AppBarConfiguration(topLevelDestinations)

        // Setup the Bottom Navigation View and the Action Bar.
        setupBottomNavMenu(navController)
        setupActionBarWithNavController(navController, appBarConfiguration)
    }

    /** Methods */

    override fun onSupportNavigateUp(): Boolean {
        val navController = findNavController(R.id.navHostFragment)
        return navController.navigateUp(appBarConfiguration) || super.onSupportNavigateUp()
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_search, menu)
        return true
    }

    private fun setupBottomNavMenu(navController: NavController) {
        bottomNavigationView.setupWithNavController(navController)
    }

}
