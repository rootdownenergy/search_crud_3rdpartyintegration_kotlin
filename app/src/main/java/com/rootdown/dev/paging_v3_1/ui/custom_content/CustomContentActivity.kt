package com.rootdown.dev.paging_v3_1.ui.custom_content

import android.os.Build
import android.os.Bundle
import androidx.activity.viewModels
import androidx.annotation.RequiresApi
import androidx.fragment.app.FragmentActivity
import com.google.android.material.tabs.TabLayoutMediator
import com.rootdown.dev.paging_v3_1.R
import com.rootdown.dev.paging_v3_1.databinding.ActivityCustomContentBinding
import com.rootdown.dev.paging_v3_1.ui.user.UserContentViewModel
import dagger.hilt.android.AndroidEntryPoint



@AndroidEntryPoint
class CustomContentActivity : FragmentActivity() {


    val tabs = arrayOf("Saved", "Created")
    private val userViewModel: UserContentViewModel by viewModels()
    private lateinit var binding: ActivityCustomContentBinding
    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        binding = ActivityCustomContentBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        val toolbar: androidx.appcompat.widget.Toolbar = findViewById(R.id.toolbar);

        toolbar.setNavigationIcon(R.drawable.ic_baseline_star_24)
        toolbar.setNavigationOnClickListener {
            //Toast.makeText(applicationContext,"Navigation icon was clicked",Toast.LENGTH_SHORT).show()
        }

        binding.toolbar.inflateMenu(R.menu.bottom_nav_menu)

        val viewPager = binding.viewPager
        val tabLayout = binding.tabLayout

        val adapter = CustomcontentAdapter(supportFragmentManager, lifecycle)
        viewPager.adapter = adapter

        TabLayoutMediator(tabLayout, viewPager) { tab, position ->
            tab.text = tabs[position]
        }.attach()
    }
}