package com.rootdown.dev.paging_v3_1.ui.user

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import com.google.android.material.snackbar.Snackbar
import com.rootdown.dev.paging_v3_1.data.UserRepo
import com.rootdown.dev.paging_v3_1.databinding.FragmentUserEditProfileBinding
import com.rootdown.dev.paging_v3_1.ui.MainActivity
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch
import kotlin.properties.Delegates

@AndroidEntryPoint
class FragmentUserEditProfile: Fragment() {
    private var _binding: FragmentUserEditProfileBinding? = null
    private val binding get() = _binding!!
    private var lat by Delegates.notNull<Float>()
    private var lng by Delegates.notNull<Float>()
    private lateinit var name: String
    private var proId by Delegates.notNull<Long>()


    private val userViewModel: UserContentViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        (activity as MainActivity).supportActionBar?.title = ""
    }
    override fun onResume() {
        super.onResume()
        // Set title bar
        (activity as MainActivity)
            .setActionBarTitle("")
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentUserEditProfileBinding.inflate(inflater, container, false)
        val view = binding.root
        val fab: View = binding.fab

        userViewModel.profileEditDetailed.observe(viewLifecycleOwner){
            if (it != null) {
                proId = it.id
                lat = it.lat.toFloat()
                lng = it.lng.toFloat()
                name = it.company_name
                _binding?.repoDetailName?.text = it.company_name
                _binding?.repoDetailDescription?.text = it.company_description
            }
        }
        binding.btnDetailGeo.setOnClickListener {
            val lat: Float = this.lat
            val lng: Float = this.lng
            val title: String = this.name
            val action = FragmentUserEditProfileDirections.actionEditProfileToMapsFragment2(lat, lng, title)
            this.findNavController().navigate(action)
        }
        fab.setOnClickListener { view ->
            val proId = proId
            val x = userViewModel.checkNullProfileId()
            lifecycleScope.launch {
                userViewModel.deleteUserProfile(x)
            }
            this.findNavController().navigateUp()
        }
        return view
    }
    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}