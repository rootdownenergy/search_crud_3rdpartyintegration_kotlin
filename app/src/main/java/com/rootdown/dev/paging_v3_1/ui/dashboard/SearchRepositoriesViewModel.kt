package com.rootdown.dev.paging_v3_1.ui.dashboard

import androidx.lifecycle.*
import androidx.paging.*
import com.rootdown.dev.paging_v3_1.data.Repo
import com.rootdown.dev.paging_v3_1.repo.RootDownRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

@HiltViewModel
class SearchRepositoriesViewModel @Inject constructor(
    private val repository: RootDownRepository,
) : ViewModel() {

    private var currentQueryValue: String? = null
    private var currentSearchResult: Flow<PagingData<UiModel>>? = null
    val x_profiles: LiveData<List<Repo>> = repository.profile_ls

    @ExperimentalPagingApi
    fun searchRepo(queryString: String): Flow<PagingData<UiModel>> {
        val lastResult = currentSearchResult
        if (queryString == currentQueryValue && lastResult != null) {
            return lastResult
        }
        currentQueryValue = queryString
        val newResult: Flow<PagingData<UiModel>> = repository.getSearchResultStream(queryString)
            .map { pagingData -> pagingData.map { UiModel.RepoItem(it) } }
            .map {
                it.insertSeparators<UiModel.RepoItem, UiModel> { before, after ->
                    if (after == null) {
                        // we're at the end of the list
                        return@insertSeparators null
                    }

                    if (before == null) {
                        // we're at the beginning of the list
                        //return@insertSeparators UiModel.SeparatorItem("${after.roundedStarCount}0.000+ stars")
                        return@insertSeparators null
                    }
                    // check between 2 items
                    if (before.roundedStarCount!! > after.roundedStarCount!!) {
                        if (after.roundedStarCount!! >= 1) {
                            return@insertSeparators null
                            //UiModel.SeparatorItem("${after.roundedStarCount}0.000+ stars")
                        } else {
                            return@insertSeparators null
                            //UiModel.SeparatorItem("< 10.000+ stars")
                        }
                    } else {
                        // no separator
                        null
                    }
                }
            }
            .cachedIn(viewModelScope)
        currentSearchResult = newResult
        return newResult
    }
}

sealed class UiModel {
    data class RepoItem(val repo: Repo) : UiModel()
    data class SeparatorItem(val description: String) : UiModel()
}
private const val NETWORK_PAGE_SIZE = 50
private val UiModel.RepoItem.roundedStarCount: Int?
    get() = this.repo.rating?.div(10_000)
