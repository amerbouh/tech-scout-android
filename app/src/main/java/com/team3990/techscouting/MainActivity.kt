package com.team3990.techscouting

import android.app.SearchManager
import android.bluetooth.*
import android.content.ComponentName
import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.widget.SearchView
import androidx.navigation.NavController
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.navigateUp
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import com.team3990.techscouting.bluetooth.ApplicationBluetoothServer
import com.team3990.techscouting.model.MatchData
import com.team3990.techscouting.ui.search.SearchActivity
import com.team3990.techscouting.util.Endgame
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.coroutines.flow.asFlow
import java.util.*

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

        // Get an instance of the search action and the search
        // view.
        val searchMenuItem = menu?.findItem(R.id.app_bar_search)
        val searchView = searchMenuItem?.actionView as SearchView

        // Get an instance of the search manager and the component name.
        val searchManager = getSystemService(Context.SEARCH_SERVICE) as SearchManager
        val componentName = ComponentName(this, SearchActivity::class.java)

        // Load the search configuration and assign it to the search view.
        searchView.setSearchableInfo( searchManager.getSearchableInfo(componentName))

        return true
    }

    private fun setupBottomNavMenu(navController: NavController) {
        bottomNavigationView.setupWithNavController(navController)
    }

}
