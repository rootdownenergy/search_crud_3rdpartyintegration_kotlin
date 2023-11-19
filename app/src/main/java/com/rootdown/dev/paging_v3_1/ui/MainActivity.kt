package com.rootdown.dev.paging_v3_1.ui

import android.content.pm.ApplicationInfo
import android.content.pm.PackageManager
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.drawerlayout.widget.DrawerLayout
import androidx.navigation.findNavController
import androidx.navigation.ui.NavigationUI
import com.rootdown.dev.paging_v3_1.R
import com.rootdown.dev.paging_v3_1.databinding.ActivityMainBinding
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class MainActivity: AppCompatActivity() {

    private lateinit var drawerLayout: DrawerLayout
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)
        val ai: ApplicationInfo = applicationContext.packageManager
            .getApplicationInfo(applicationContext.packageName, PackageManager.GET_META_DATA)

        drawerLayout = binding.container
        val navController = findNavController(R.id.nav_host_fragment)
        var vxx: String = ""
        val intent = intent
        intent.getStringExtra("frag")?.let {
            vxx=it
            Log.w("*****", vxx)
        }
        //when (value) {
            //"HomeFragment" -> { navController.navigate(R.id.navigation_home) }
            //"SearchReposFragment" -> { navController.navigate(R.id.navigation_dashboard) }
            //"MapsFragment" -> { navController.navigate(R.id.mapsFragment) }
            //"UserStrainsFragment" -> { navController.navigate(R.id.nav_user_strains)}
            //"UserProfilesFragment" -> { navController.navigate(R.id.nav_user_profiles) }
        //}
        //supportActionBar?.title = ""
        //setContentView(R.layout.activity_main)
        //val navView: BottomNavigationView = findViewById(R.id.nav_view)

        // Passing each menu ID as a set of Ids because each
        // menu should be considered as top level destinations.
        //val drawerLayout: DrawerLayout = findViewById(R.id.container)
        //val appBarConfiguration = AppBarConfiguration(setOf(
        //R.id.navigation_home, R.id.navigation_dashboard, R.id.mapsFragment), drawerLayout)
        NavigationUI.setupActionBarWithNavController(this, navController, drawerLayout)
        //navView.setupWithNavController(navController)
        NavigationUI.setupWithNavController(binding.navView, navController)
    }
    override fun onSupportNavigateUp(): Boolean {
        val navController = this.findNavController(R.id.nav_host_fragment)
        return NavigationUI.navigateUp(navController, drawerLayout)
    }
    fun setActionBarTitle(title: String?) {
        supportActionBar!!.title = title
    }
}