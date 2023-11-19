package com.rootdown.dev.paging_v3_1.data

import androidx.paging.ExperimentalPagingApi
import androidx.paging.LoadType
import androidx.paging.PagingState
import androidx.paging.RemoteMediator
import androidx.room.withTransaction
import com.rootdown.dev.paging_v3_1.api.RootDownService
import com.rootdown.dev.paging_v3_1.db.RemoteKeys
import com.rootdown.dev.paging_v3_1.db.RepoDatabase

import retrofit2.HttpException
import java.io.IOException
import java.io.InvalidObjectException

private const val ROOTDOWN_STARTING_PAGE_INDEX = 1
@OptIn(ExperimentalPagingApi::class)
class RootDownRemoteMediator(
    private val query: String,
    private val service: RootDownService,
    private val repoDatabase: RepoDatabase
) : RemoteMediator<Int, Repo>() {

    override suspend fun load(loadType: LoadType, state: PagingState<Int, Repo>): MediatorResult
    {

        val page = when (loadType) {
            LoadType.REFRESH -> {
                val remoteKeys = getRemoteKeyClosestToCurrentPosition(state)
                remoteKeys?.nextKey?.minus(1) ?: ROOTDOWN_STARTING_PAGE_INDEX
            }
            LoadType.PREPEND -> {
                var remoteKeys = getRemoteKeyFromFirstItem(state)
                if (remoteKeys == null) {
                    remoteKeys = RemoteKeys(0L,0,1)
                }
                val prevKey = remoteKeys.prevKey ?: return MediatorResult.Success(endOfPaginationReached = true)
                remoteKeys.prevKey
            }
            LoadType.APPEND -> {
                var remoteKeys = getRemoteKeyFromLastItem(state)
                if (remoteKeys?.nextKey == null) {
                    remoteKeys = RemoteKeys(0L,0,1)
                    remoteKeys.nextKey
                    //throw InvalidObjectException("Remote key should not be null for $loadType")
                }
                remoteKeys.nextKey
            }
        }
        val apiQuery = query

        try {
            val apiResponse = service.searchRepos(apiQuery, page!!, state.config.pageSize)
            val repos = apiResponse.items
            val endOfPaginationReached = repos.isEmpty()
            repoDatabase.withTransaction {
                // clear all tables in the database
                if (loadType == LoadType.REFRESH) {
                    repoDatabase.remoteKeysDao().clearRemoteKeys()
                    repoDatabase.reposDao().clearRepos()
                }
                val prevKey = if (page == ROOTDOWN_STARTING_PAGE_INDEX) null else page - 1
                val nextKey = if (endOfPaginationReached) null else page + 1
                val keys = repos.map {
                    RemoteKeys(repoId = it.id, prevKey = prevKey, nextKey = nextKey)
                }
                repoDatabase.remoteKeysDao().insertAll(keys)
                repoDatabase.reposDao().insertAll(repos)
            }
            return MediatorResult.Success(endOfPaginationReached = endOfPaginationReached)
        } catch (exception: IOException) {
            return MediatorResult.Error(exception)
        } catch (exception: HttpException) {
            return MediatorResult.Error(exception)
        }
    }


    private suspend fun getRemoteKeyFromLastItem(state: PagingState<Int, Repo>): RemoteKeys? {
        return state.pages.lastOrNull() { it.data.isNotEmpty() }?.data?.lastOrNull()
            ?.let { repo ->
                repoDatabase.remoteKeysDao().remoteKeysRepoId(repo.id)
            }
    }
    private suspend fun getRemoteKeyFromFirstItem(state: PagingState<Int, Repo>): RemoteKeys? {
        return state.pages.firstOrNull { it.data.isNotEmpty() }?.data?.firstOrNull()
            ?.let { repo ->
                repoDatabase.remoteKeysDao().remoteKeysRepoId(repo.id)
            }
    }
    private suspend fun getRemoteKeyClosestToCurrentPosition(
        state: PagingState<Int, Repo>
    ): RemoteKeys? {
        return state.anchorPosition?.let { position ->
            state.closestItemToPosition(position)?.id?.let { repoId ->
                repoDatabase.remoteKeysDao().remoteKeysRepoId(repoId)
            }
        }
    }
}