package com.rootdown.dev.paging_v3_1.ui.user

import android.os.Bundle
import android.view.*
import android.widget.LinearLayout
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.transition.TransitionInflater
import androidx.viewpager2.widget.ViewPager2
import com.airbnb.epoxy.EpoxyRecyclerView
import com.rootdown.dev.paging_v3_1.R
import com.rootdown.dev.paging_v3_1.api.UserStrain
import com.rootdown.dev.paging_v3_1.databinding.FragmentUserStrainsBinding
import com.rootdown.dev.paging_v3_1.ui.MainActivity
import com.rootdown.dev.paging_v3_1.user
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class UserStrainsFragment: Fragment(R.layout.fragment_user_strains) {

    private lateinit var binding: FragmentUserStrainsBinding

    private lateinit var demoCollectionAdapter: StrainCreatePagerAdapter
    private lateinit var viewPager: ViewPager2


    private val viewModel: UserContentViewModel by viewModels()
    //private val adapter = UserStrainsAdapter{position -> onListItemClick(position)}
    //var strain_ls: List<UserStrain> = emptyList()
    //private var searchJob: Job? = null


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val inflater = TransitionInflater.from(requireContext())
        exitTransition = inflater.inflateTransition(R.transition.fade)
        setHasOptionsMenu(true)
        (activity as MainActivity).supportActionBar?.title = "SAVED STRAINS"
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentUserStrainsBinding.inflate(inflater)
        val epoxyView: EpoxyRecyclerView = binding.userStrainLs

        viewModel.updatedStrains.observe(viewLifecycleOwner){
            it.let {
                setupRecyclerView(it, epoxyView)
            }
        }
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        val x = view.findViewById<LinearLayout>(R.id.userStrainsLayout)
        //x.orientation
        super.onViewCreated(view, savedInstanceState)
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        super.onCreateOptionsMenu(menu, inflater)
    }

    override fun onResume() {
        super.onResume()
        setHasOptionsMenu(true)
        (activity as MainActivity).supportActionBar?.title = "SAVED STRAINS"
    }

    private fun setupRecyclerView(x: List<UserStrain>, epoxy: EpoxyRecyclerView) {

        epoxy.withModels {
            x.forEach { currStrain ->
                user {
                    id(currStrain.id)
                    strain(currStrain)
                    clickListener { x ->
                        //Toast.makeText(activity, "Calls Icon Click ${profile.toString()}", Toast.LENGTH_LONG).show()
                        onListItemClick(currStrain.strain_name)
                    }
                }
            }
        }
    }

    private fun onListItemClick(arg: String?) {
        //val current_repo: UserProfile = profile_ls[x]
        this.findNavController().navigate(
            R.id.action_nav_user_strains_to_fragment_user_edit_strain,
            bundleOf("strId" to arg)
        )
    }
}