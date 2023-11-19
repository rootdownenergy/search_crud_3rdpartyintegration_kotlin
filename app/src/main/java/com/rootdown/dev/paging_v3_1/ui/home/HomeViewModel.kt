package com.rootdown.dev.paging_v3_1.ui.home

import androidx.lifecycle.*
import com.rootdown.dev.paging_v3_1.data.DatabaseStrain
import com.rootdown.dev.paging_v3_1.data.Repo
import com.rootdown.dev.paging_v3_1.db.RepoDatabase
import com.rootdown.dev.paging_v3_1.repo.ProfileRepository
import com.rootdown.dev.paging_v3_1.repo.RootDownRepository
import com.rootdown.dev.paging_v3_1.repo.StrainsRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.launch
import java.io.IOException
import javax.inject.Inject


@HiltViewModel
class HomeViewModel @Inject constructor(
    private val strainsRepo: StrainsRepository,
    private val profileRepo: ProfileRepository,
    state: SavedStateHandle
) : ViewModel() {

    /**
     * The data source this ViewModel will fetch results from.
     */
    //private val strainsRepository = StrainsRepository(RepoDatabase.getInstance(application))
    //private val profileRepository = ProfileRepository(RepoDatabase.getInstance(application))

    val strain = strainsRepo.strain
    val strainId = state.get<String>("strainId")
    val strainDetailed: LiveData<DatabaseStrain> = strainsRepo.getStrain(strainId)

    val profile = profileRepo
    val getProfileId = state.get<String?>("profile")
    val profileDetailed: LiveData<Repo> = profileRepo.getProfile(getProfileId)


    private var currentQueryValue: String? = null
    private var currentSearchResult: Flow<List<DatabaseStrain>>? = null

    fun searchStrainType(q: String): Flow<List<DatabaseStrain>> {
        val lastResult = currentSearchResult
        if (q == currentQueryValue && lastResult != null) {
            return lastResult
        }
        currentQueryValue = q
        val newResult: Flow<List<DatabaseStrain>> = strainsRepo.getSearchResultStream(q)
        return newResult
    }
    init {
        refreshDataFromRepository()
    }

    private fun refreshDataFromRepository() {
        viewModelScope.launch {
            try {
                strainsRepo.refreshStrains()

            } catch (networkError: IOException) {
                // Show a Toast error message and hide the progress bar.
            }
        }
    }

}

sealed class UiModel {
    data class StrainItem(val strain: DatabaseStrain) : UiModel()
    data class SeparatorItem(val description: String) : UiModel()
}