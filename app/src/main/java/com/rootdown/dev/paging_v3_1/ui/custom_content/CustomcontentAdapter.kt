package com.rootdown.dev.paging_v3_1.ui.custom_content

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.lifecycle.Lifecycle
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.rootdown.dev.paging_v3_1.ui.geomapper.CreateImageTabFragment
import com.rootdown.dev.paging_v3_1.ui.geomapper.UserContentFragment


private const val NUM_TABS = 2

class CustomcontentAdapter(fragmentManager: FragmentManager, lifecycle: Lifecycle) :
    FragmentStateAdapter(fragmentManager, lifecycle)  {

    override fun getItemCount(): Int {
        return NUM_TABS
    }

    override fun createFragment(position: Int): Fragment {
        when (position) {
            0 -> return UserContentFragment()
        }
        return CreateImageTabFragment()
    }

}