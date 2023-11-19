package com.rootdown.dev.paging_v3_1.ui.home

import android.annotation.SuppressLint
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.lifecycle.lifecycleScope
import androidx.transition.TransitionInflater
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.google.android.material.snackbar.Snackbar
import com.rootdown.dev.paging_v3_1.R
import com.rootdown.dev.paging_v3_1.data.DatabaseStrain
import com.rootdown.dev.paging_v3_1.data.UserDatabaseStrain
import com.rootdown.dev.paging_v3_1.databinding.FragmentStrainsDetailsBinding
import com.rootdown.dev.paging_v3_1.ui.MainActivity
import com.rootdown.dev.paging_v3_1.ui.user.UserContentViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch
import kotlin.properties.Delegates


@AndroidEntryPoint
class StrainsDetailsFragment: Fragment() {

    private var _binding: FragmentStrainsDetailsBinding? = null
    private val binding get() = _binding!!
    private var userStrainId by Delegates.notNull<Int>()
    private var userStrainName by Delegates.notNull<String>()
    private lateinit var strain: DatabaseStrain

    private val viewModel: HomeViewModel by viewModels()
    private val userViewModel: UserContentViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val inflater = TransitionInflater.from(requireContext())
        exitTransition = inflater.inflateTransition(R.transition.fade)
        setHasOptionsMenu(true)
        (activity as MainActivity).supportActionBar?.title = ""
    }

    @SuppressLint("ResourceAsColor")
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentStrainsDetailsBinding.inflate(inflater, container, false)
        val view = binding.root
        val fab: View = binding.saveStrain
        var xx = ""
        val img: ImageView = _binding!!.strainDetailImgId
        viewModel.strainDetailed.observe(viewLifecycleOwner, Observer {
            val strainId = it.id
            userStrainId = strainId
            userStrainName = it.strain_name.removeSuffix(".webp")
            xx = it.strain_name.trimEnd().removeSuffix("webp")
            val currStrain = DatabaseStrain(it.id, it.strainOwnerId, xx,
                it.strain_description, it.thc, it.cbd, it.cbn, it.strain_tag_words, it.strain_image,
                it.strain_type, it.updated_at, it.created_at)
            strain = currStrain
            val it_strain_img = it.strain_image
            val uri = "https://d3b3tm7jjqus71.cloudfront.net/$it_strain_img"
            Log.w("STR$", uri)
            if(it_strain_img != null){
                Glide.with(this)
                    .load(uri)
                    .diskCacheStrategy(DiskCacheStrategy.ALL)
                    .into(img)
            }
            _binding!!.strainDetailNameId.text = it.strain_name.removeSuffix(".webp")
        })
        fab.setOnClickListener { view ->
            val currStrain = strain

            val id: Int = currStrain.id
            val strainOwnerId: Int? = currStrain.strainOwnerId
            val strain_name: String = currStrain.strain_name.removeSuffix(".webp")
            val strain_description: String? = currStrain.strain_description
            val thc: String? = currStrain.thc
            val cbd: String? = currStrain.cbd
            val cbn: String? = currStrain.cbn
            val strain_tag_words: String? = currStrain.strain_tag_words
            val strain_image: String? = currStrain.strain_image
            val strain_type: String? = currStrain.strain_type
            val updated_at: String? = currStrain.updated_at
            val created_at: String? = currStrain.created_at
            val strainSuper = UserDatabaseStrain(id, strainOwnerId, strain_name, strain_description,
                thc, cbd, cbn, strain_tag_words, strain_image, strain_type, created_at, updated_at)

            userViewModel.insertDatabaseStrain(strainSuper)

            Snackbar.make(binding.strainDetailsCoordinatorLayout, "Delete ${strain_name}?", Snackbar.LENGTH_LONG)
                .setActionTextColor(R.color.white)
                .setAction("Undo", DeleteStrainListener(id))
                .show()
        }
        return view
    }

    override fun onResume() {
        super.onResume()
        // Set title bar
        (activity as MainActivity)
            .setActionBarTitle("")
    }
    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    inner class DeleteStrainListener(id: Int): View.OnClickListener{
        val strId: Int = id
        override fun onClick(v: View?) {
            lifecycleScope.launch {
                userViewModel.deleteUserStrain(strId)
            }
        }

    }
}