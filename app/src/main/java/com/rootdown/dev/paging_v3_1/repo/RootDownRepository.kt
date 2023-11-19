package com.rootdown.dev.paging_v3_1.repo

import androidx.lifecycle.LiveData
import androidx.paging.ExperimentalPagingApi
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.rootdown.dev.paging_v3_1.api.RootDownService
import com.rootdown.dev.paging_v3_1.data.Repo
import com.rootdown.dev.paging_v3_1.data.RootDownRemoteMediator
import com.rootdown.dev.paging_v3_1.db.RepoDatabase
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject


private const val ROOTDOWN_STARTING_PAGE_INDEX = 1

/**
 * Local and repo data
 *
 */
class RootDownRepository @Inject constructor(
    private val service: RootDownService,
    private val database: RepoDatabase
) {
    val profile_ls: LiveData<List<Repo>> = database.reposDao().getprofiles()
    @ExperimentalPagingApi
    fun getSearchResultStream(query: String): Flow<PagingData<Repo>> {
        val dbQuery = "%${query.replace(' ', '%')}%"
        val pagingSourceFactory =  { database.reposDao().reposByName(dbQuery)}
        return Pager(
            config = PagingConfig(pageSize = NETWORK_PAGE_SIZE, enablePlaceholders = false),
            remoteMediator = RootDownRemoteMediator(
                query,
                service,
                database
            ),
            pagingSourceFactory = pagingSourceFactory
        ).flow
    }
    companion object {
        private const val NETWORK_PAGE_SIZE = 50
    }
}