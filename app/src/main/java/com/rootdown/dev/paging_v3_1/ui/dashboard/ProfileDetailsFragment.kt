package com.rootdown.dev.paging_v3_1.ui.dashboard

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import com.google.android.material.snackbar.Snackbar
import com.rootdown.dev.paging_v3_1.R
import com.rootdown.dev.paging_v3_1.data.Repo
import com.rootdown.dev.paging_v3_1.data.UserRepo
import com.rootdown.dev.paging_v3_1.databinding.FragmentProfileDetailsBinding
import com.rootdown.dev.paging_v3_1.ui.MainActivity
import com.rootdown.dev.paging_v3_1.ui.home.HomeViewModel
import com.rootdown.dev.paging_v3_1.ui.user.UserContentViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch
import kotlin.properties.Delegates


@AndroidEntryPoint
class ProfileDetailsFragment: Fragment() {
    private var _binding: FragmentProfileDetailsBinding? = null
    private val binding get() = _binding!!
    private lateinit var profileId: String
    private var userProfileId by Delegates.notNull<Long>()
    private var userProfileName by Delegates.notNull<String>()
    private lateinit var repo: Repo
    private var lat by Delegates.notNull<Float>()
    private var lng by Delegates.notNull<Float>()
    private lateinit var name: String

    private val viewModel: HomeViewModel by viewModels()
    private val userViewModel: UserContentViewModel by viewModels()


    @SuppressLint("ResourceAsColor")
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentProfileDetailsBinding.inflate(inflater, container, false)
        val view = binding.root
        val fab: View = binding.fab

        viewModel.profileDetailed.observe(viewLifecycleOwner, Observer {
            lat = it.lat.toFloat()
            lng = it.lng.toFloat()
            name = it.company_name
            userProfileId = it.id
            userProfileName = it.company_name
            val currRepo = Repo(it.id, it.profileOwnerId, it.menu_num_id, it.company_name,
                it.email_address, it.address, it.unit_number, it.city, it.state, it.zip,
                it.status, it.contact_website, it.hours_of_operation_sunday, it.hours_of_operation_monday,
                it.hours_of_operation_tuesday, it.hours_of_operation_wednesday, it.hours_of_operation_thursday,
                it.hours_of_operation_friday, it.hours_of_operation_saturday, it.rating, it.medical,
                it.recreational, it.ada, it.delivery, it.delivery_only, it.cbd, it.edibles,
                it.concentrates, it.cc, it.company_description, it.phone_number, it.lab_tested,
                it.clones, it.seeds, it.paid_tier, it.lat, it.lng, it.created_at, it.updated_at)
            repo = currRepo
            profileId = it.company_name
            _binding?.repoDetailName?.text = it.company_name
            _binding?.repoDetailDescription?.text = it.company_description
        })
        binding.btnDetailGeo.setOnClickListener {
            val lat: Float = this.lat
            val lng: Float = this.lng
            val title: String = this.name
            val action = ProfileDetailsFragmentDirections.actionNavigationProfileToMapsFragment(lat, lng, title)
            this.findNavController().navigate(action)
        }
        fab.setOnClickListener { view ->
            val currRepo = repo
            val id: Long = currRepo.id
            val profileOwnerId: Long? = currRepo.profileOwnerId
            val menu_num_id: Int? = currRepo.menu_num_id
            val company_name: String = currRepo.company_name
            val email_address: String = currRepo.email_address
            val address: String? = currRepo.address
            val unit_number: String? = currRepo.unit_number
            val city: String = currRepo.city
            val state: String = currRepo.state
            val zip: Int = currRepo.zip
            val status: String = currRepo.status
            val contact_website: String? = currRepo.contact_website
            val hours_of_operation_sunday: String? = currRepo.hours_of_operation_sunday
            val hours_of_operation_monday: String? = currRepo.hours_of_operation_monday
            val hours_of_operation_tuesday: String? = currRepo.hours_of_operation_tuesday
            val hours_of_operation_wednesday: String? = currRepo.hours_of_operation_wednesday
            val hours_of_operation_thursday: String? = currRepo.hours_of_operation_thursday
            val hours_of_operation_friday: String? = currRepo.hours_of_operation_friday
            val hours_of_operation_saturday: String? = currRepo.hours_of_operation_saturday
            val rating: Int? = currRepo.rating
            val medical: Byte? = currRepo.medical
            val recreational: Byte? = currRepo.recreational
            val ada: Byte? = currRepo.ada
            val delivery: Byte? = currRepo.delivery
            val delivery_only: Byte? = currRepo.delivery_only
            val cbd: Byte? = currRepo.cbd
            val edibles: Byte? = currRepo.edibles
            val concentrates: Byte? = currRepo.concentrates
            val cc: Byte? = currRepo.cc
            val company_description: String? = currRepo.company_description
            val phone_number: String? = currRepo.phone_number
            val lab_tested: Byte? = currRepo.lab_tested
            val clones: Byte? = currRepo.clones
            val seeds: Byte? = currRepo.seeds
            val paid_tier: Byte? = currRepo.paid_tier
            val lat: Double = currRepo.lat
            val lng: Double = currRepo.lng
            val created_at: String? = currRepo.created_at
            val updated_at: String? = currRepo.updated_at

            userViewModel.updateUserProfile(currRepo.id, profileOwnerId = 1L, currRepo.menu_num_id,
                                            currRepo.company_name, currRepo.email_address, currRepo.email_address,
                                            currRepo.unit_number, currRepo.city, currRepo.state, currRepo.zip,
                                            currRepo.status, currRepo.contact_website, currRepo.hours_of_operation_sunday, currRepo.hours_of_operation_monday,
            currRepo.hours_of_operation_tuesday, currRepo.hours_of_operation_wednesday, currRepo.hours_of_operation_thursday, currRepo.hours_of_operation_friday,
            currRepo.hours_of_operation_saturday, currRepo.rating, currRepo.medical, currRepo.recreational, currRepo.ada, currRepo.delivery, currRepo.delivery_only,
            currRepo.cbd, currRepo.edibles, currRepo.concentrates, currRepo.cc, currRepo.company_description, currRepo.phone_number, currRepo.lab_tested, currRepo.clones,
            currRepo.seeds, currRepo.paid_tier, currRepo.lat, currRepo.lng, currRepo.created_at, currRepo.updated_at)

            val profileSuper = UserRepo(id, profileOwnerId, menu_num_id, company_name, email_address, address,
                unit_number, city, state, zip, status, contact_website, hours_of_operation_sunday,
                hours_of_operation_monday, hours_of_operation_tuesday, hours_of_operation_wednesday,
                hours_of_operation_thursday, hours_of_operation_friday, hours_of_operation_saturday,
                rating, medical, recreational, ada, delivery,
                delivery_only, cbd, edibles, concentrates, cc, company_description,
                phone_number, lab_tested, clones, seeds, paid_tier, lat, lng, created_at, updated_at)

            userViewModel.insertProfile(profileSuper)

            Snackbar.make(binding.profileDetailsCoordinatorLayout, "Delete ${company_name}?", Snackbar.LENGTH_LONG)
                .setActionTextColor(R.color.white)
                .setAction("Undo", ProfileDeleteListener(id))
                .show()
        }
        return view
    }
    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
    inner class ProfileDeleteListener(id: Long): View.OnClickListener{
        val proId: Long = id
        override fun onClick(v: View?) {
            lifecycleScope.launch {
                userViewModel.deleteUserProfile(proId)
            }
        }
    }

}