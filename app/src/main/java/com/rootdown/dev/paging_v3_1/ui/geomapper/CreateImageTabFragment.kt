package com.rootdown.dev.paging_v3_1.ui.geomapper

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.*
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.GridLayoutManager
import androidx.transition.TransitionInflater
import com.rootdown.dev.paging_v3_1.R
import com.rootdown.dev.paging_v3_1.databinding.FragmentCreateImageTabBinding
import com.rootdown.dev.paging_v3_1.ui.MainActivity
import com.rootdown.dev.paging_v3_1.ui.user.UserContentViewModel
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class CreateImageTabFragment : Fragment(){

    private lateinit var binding: FragmentCreateImageTabBinding
    private val viewModel: UserContentViewModel by viewModels()
    private val adapterUserContent = ContentUserIconsAdapter{position ->  onListItemClick(position)}

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val inflater = TransitionInflater.from(requireContext())
        exitTransition = inflater.inflateTransition(R.transition.fade)
        setHasOptionsMenu(true)
        viewModel.refreshFilesList()
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentCreateImageTabBinding.inflate(inflater, container, false)
        val view = binding.root
        val fab = binding.createBitmap
        binding.userContentImgsRv.apply {
            layoutManager = GridLayoutManager(context, 2)
            adapter = adapterUserContent
        }
        fab.setOnClickListener {
            activity?.let{
                val intent = Intent (it, GeomapperActivity::class.java)
                it.startActivity(intent)
            }
        }
        return view
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        viewModel.filesLiveData.observe(viewLifecycleOwner, Observer {
            Log.w("A$$$", it.toString())
            adapterUserContent.iiLs = it
        })
        super.onViewCreated(view, savedInstanceState)
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


    private fun onListItemClick(position: Int) {

    }
}