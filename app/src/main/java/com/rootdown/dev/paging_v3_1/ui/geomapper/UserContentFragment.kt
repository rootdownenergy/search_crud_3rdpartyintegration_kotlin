package com.rootdown.dev.paging_v3_1.ui.geomapper

import android.content.Intent
import android.os.Bundle
import android.view.*
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.transition.TransitionInflater
import com.airbnb.epoxy.EpoxyRecyclerView
import com.rootdown.dev.paging_v3_1.R
import com.rootdown.dev.paging_v3_1.api.UserStrain
import com.rootdown.dev.paging_v3_1.databinding.FragmentUserContentBinding
import com.rootdown.dev.paging_v3_1.ui.MainActivity
import com.rootdown.dev.paging_v3_1.ui.user.UserContentViewModel
import com.rootdown.dev.paging_v3_1.user
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class UserContentFragment : Fragment() {
    private lateinit var binding: FragmentUserContentBinding
    private val viewModel: UserContentViewModel by viewModels()
    var topStrains: List<UserStrain> = emptyList()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val inflater = TransitionInflater.from(requireContext())
        exitTransition = inflater.inflateTransition(R.transition.fade)
        setHasOptionsMenu(true)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentUserContentBinding.inflate(inflater)
        val epoxyView: EpoxyRecyclerView = binding.rvUserContent

        viewModel.updatedStrains.observe(viewLifecycleOwner){
            it.let {
                setupRecyclerView(it, epoxyView)
            }
        }
        return binding.root

    }
    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.bottom_nav_menu, menu)
    }

    override fun onOptionsItemSelected(item: MenuItem) = when (item.itemId) {
        R.id.navigation_home -> {
            Toast.makeText(activity, "HOME", Toast.LENGTH_SHORT).show()
            val i = Intent(activity, MainActivity::class.java)
            val fragHome = "HomeFragment"
            i.putExtra("frag", fragHome)
            startActivity(i)
            true
        }
        R.id.navigation_dashboard -> {
            val i = Intent(activity, MainActivity::class.java)
            val fragSearch = "SearchReposFragment"
            i.putExtra("frag", fragSearch)
            startActivity(i)
            true
        }
        R.id.mapsFragment -> {
            val i = Intent(activity, MainActivity::class.java)
            val fragMapMain = "MapsFragment"
            i.putExtra("frag", fragMapMain)
            startActivity(i)
            true
        }
        R.id.nav_user_strains -> {
            val i = Intent(activity, MainActivity::class.java)
            val fragUserStrains = "UserStrainsFragment"
            i.putExtra("frag", fragUserStrains)
            startActivity(i)
            true
        }
        R.id.nav_user_profiles -> {
            val i = Intent(activity, MainActivity::class.java)
            val fragUserProfiles = "UserProfilesFragment"
            i.putExtra("frag", fragUserProfiles)
            startActivity(i)
            true
        }
        else -> super.onOptionsItemSelected(item)
    }

    private fun setupRecyclerView(x: List<UserStrain>, epoxy: EpoxyRecyclerView) {

        epoxy.withModels {
            x.forEach { currStrain ->
                user {
                    id(currStrain.id)
                    strain(currStrain)
                    clickListener { x ->
                        Toast.makeText(activity, "Get Strain ${currStrain.strain_name}", Toast.LENGTH_LONG).show()
                        //onListItemClick(currStrain.strain_name)
                    }
                }
            }
        }
    }
}