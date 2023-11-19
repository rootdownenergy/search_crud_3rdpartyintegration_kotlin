package com.rootdown.dev.paging_v3_1.ui.home


import android.app.Activity
import android.os.Bundle
import android.view.*
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.findNavController
import androidx.transition.TransitionInflater
import com.airbnb.epoxy.EpoxyRecyclerView
import com.airbnb.epoxy.carousel
import com.google.android.material.snackbar.Snackbar
import com.rootdown.dev.paging_v3_1.R
import com.rootdown.dev.paging_v3_1.SwitchCarouselBindingModel_
import com.rootdown.dev.paging_v3_1.api.Strain
import com.rootdown.dev.paging_v3_1.api.UserStrain
import com.rootdown.dev.paging_v3_1.strain
import com.rootdown.dev.paging_v3_1.title
import com.rootdown.dev.paging_v3_1.ui.MainActivity
import com.rootdown.dev.paging_v3_1.ui.user.UserContentViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class HomeFragment: Fragment() {
    private val viewModel: HomeViewModel by viewModels()
    private val userViewModel: UserContentViewModel by viewModels()
    var topStrains: List<UserStrain> = emptyList()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val inflater = TransitionInflater.from(requireContext())
        exitTransition = inflater.inflateTransition(R.transition.fade)
        setHasOptionsMenu(true)
        (activity as MainActivity).supportActionBar?.title = ""
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        userViewModel.updatedStrains.observe(viewLifecycleOwner){
            it?.let {
                topStrains = it
                //Toast.makeText(context, topStrains.toString(), Toast.LENGTH_LONG).show()
            }
        }
        val root = inflater.inflate(R.layout.fragment_home, container, false)
        val epoxyView: EpoxyRecyclerView = root!!.findViewById(R.id.rvTask) as EpoxyRecyclerView
        viewModel.strain.observe(viewLifecycleOwner) { strains ->
            strains?.let {
                setupRecyclerView(it, epoxyView, topStrains)
            }
        }
        return root
    }

    override fun onResume() {
        super.onResume()
        // Set title bar
        (activity as MainActivity)
            .setActionBarTitle("")
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.strain_filter_options, menu)
    }
    override fun onOptionsItemSelected(item: MenuItem) = when (item.itemId) {
        R.id.sativa -> {
            showSnackBar("Sativa", requireActivity())
            true
        }
        R.id.indica -> {
            showSnackBar("Indica", requireActivity())
            true
        }
        R.id.hybrid -> {
            showSnackBar("Hybrid", requireActivity())
            true
        }
        else -> super.onOptionsItemSelected(item)
    }


    private fun setupRecyclerView(x: List<Strain>, epoxy: EpoxyRecyclerView, z: List<UserStrain>) {
        //val rvTask = findViewById<EpoxyRecyclerView>(R.id.rvTask)
        epoxy.withModels {

            title {
                id("title-switch")
                title("Top Saved Strains")
            }

            // Carousel Item
            val carouselItemModels = z.map { x ->
                SwitchCarouselBindingModel_()
                    .id(x.toString())
                    .carouselItem(x)
                    .clickListener { topStrain ->
                        showSnackBar("Find Near Me", requireActivity())
                    }
            }



            carousel {
                id("strain-type-switch-carousel")
                models(carouselItemModels)
            }

            x.forEach { currentStrain ->
                strain {
                    id(currentStrain.id)
                    strain(currentStrain)
                    clickListener { x ->
                        val currid: String = currentStrain.strain_name.toString()
                        val action = HomeFragmentDirections.actionFragmentHomeToFragmentStrainsDetails(currid)
                        x.findNavController().navigate(action)
                    }
                }
            }
        }
    }

    fun showSnackBar(message: String?, activity: Activity?) {
        if (null != activity && null != message) {
            Snackbar.make(
                activity.findViewById(android.R.id.content),
                message, Snackbar.LENGTH_SHORT
            ).show()
        }
    }
}