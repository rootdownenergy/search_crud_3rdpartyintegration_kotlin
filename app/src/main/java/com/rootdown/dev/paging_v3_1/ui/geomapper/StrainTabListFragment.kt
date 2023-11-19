package com.rootdown.dev.paging_v3_1.ui.geomapper

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.rootdown.dev.paging_v3_1.databinding.FragmentStrainTabListBinding



class StrainTabListFragment : Fragment() {
    private lateinit var binding: FragmentStrainTabListBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentStrainTabListBinding.inflate(inflater, container, false)
        val view = binding.root
        return view
    }
}