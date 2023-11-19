package com.rootdown.dev.paging_v3_1.ui.dashboard


import android.os.Bundle
import android.view.*
import android.widget.SearchView
import android.widget.Toast
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.paging.ExperimentalPagingApi
import androidx.paging.LoadState
import com.rootdown.dev.paging_v3_1.R
import com.rootdown.dev.paging_v3_1.data.Repo
import com.rootdown.dev.paging_v3_1.databinding.FragmentSearchReposBinding
import com.rootdown.dev.paging_v3_1.ui.MainActivity
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.distinctUntilChangedBy
import kotlinx.coroutines.flow.filter
import kotlinx.coroutines.launch

@AndroidEntryPoint
class SearchReposFragment: Fragment() {

    private lateinit var binding: FragmentSearchReposBinding
    private lateinit var state: String

    private val viewModel: SearchRepositoriesViewModel by viewModels()

    private var profileLs: List<Repo> = emptyList()
    private val adapter = ReposAdapter{position -> onListItemClick(position)}
    private var searchJob: Job? = null


    private fun onListItemClick(position: Int) {
        val currentRepo: Repo = profileLs[position]
        val lat: Float = currentRepo.lat.toFloat()
        val lng: Float = currentRepo.lng.toFloat()
        val title: String = currentRepo.company_name
        val action = SearchReposFragmentDirections.actionFragmentDashboardToFragmentMaps(lat,lng,title)
        this.findNavController().navigate(action)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        val query = savedInstanceState?.getString(LAST_SEARCH_QUERY) ?: DEFAULT_QUERY
        state = query
        super.onCreate(savedInstanceState)
        setHasOptionsMenu(true)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentSearchReposBinding.inflate(inflater)
        return binding.root
    }


    @OptIn(ExperimentalPagingApi::class)
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initAdapter()
        initSearch()
        binding.retryButton.setOnClickListener { adapter.retry() }
        viewModel.x_profiles.observe(viewLifecycleOwner){
            profileLs = it
        }
    }

    @OptIn(ExperimentalPagingApi::class)
    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.options_menu_profile, menu)
        val search: MenuItem? = menu.findItem(R.id.profile_search)
        val searchView: SearchView = search?.actionView as SearchView
        searchView.queryHint = "Abbr. State, City, Dispensary Name"
        searchView.setOnQueryTextListener(object: SearchView.OnQueryTextListener{
            override fun onQueryTextChange(newText: String?): Boolean {
                return true
            }
            override fun onQueryTextSubmit(query: String?): Boolean {
                state = query.toString()
                updateRepoLsIn()
                return true
            }
        })
        super.onCreateOptionsMenu(menu, inflater)
    }


    @ExperimentalPagingApi
    private fun search(query: String) {
        searchJob?.cancel()
        searchJob = lifecycleScope.launch {
            viewModel.searchRepo(query).collectLatest {
                adapter.submitData(it)
            }
        }
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        outState.putString(LAST_SEARCH_QUERY, state)
    }

    private fun initAdapter() {
        binding.list.adapter = adapter.withLoadStateHeaderAndFooter(
            header = ReposLoadStateAdapter { adapter.retry() },
            footer = ReposLoadStateAdapter { adapter.retry() }
        )
        adapter.addLoadStateListener { loadState ->
            binding.list.isVisible = loadState.source.refresh is LoadState.NotLoading
            binding.progressBar.isVisible = loadState.source.refresh is LoadState.Loading
            binding.retryButton.isVisible = loadState.source.refresh is LoadState.Error
            val errorState = loadState.source.append as? LoadState.Error
                ?: loadState.source.prepend as? LoadState.Error
                ?: loadState.append as? LoadState.Error
                ?: loadState.prepend as? LoadState.Error
            errorState?.let {
                Toast.makeText(
                    this.activity,
                    "${it.error}",
                    Toast.LENGTH_LONG
                ).show()
            }
        }
    }

    @ExperimentalPagingApi
    private fun initSearch() {
        updateRepoLsIn()
        lifecycleScope.launch {
            adapter.loadStateFlow
                .distinctUntilChangedBy { it.refresh }
                .filter { it.refresh is LoadState.NotLoading }
                .collect { binding.list.scrollToPosition(0) }
        }
    }

    @ExperimentalPagingApi
    private fun updateRepoLsIn() {
        val x: String = state
        search(x)
    }

    override fun onDestroy() {
        super.onDestroy()
        searchJob?.cancel()
    }

    companion object {
        private const val LAST_SEARCH_QUERY: String = "last_search_query"
        private const val DEFAULT_QUERY = "denver"
    }
}