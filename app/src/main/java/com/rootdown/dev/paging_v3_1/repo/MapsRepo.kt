package com.rootdown.dev.paging_v3_1.repo

import androidx.lifecycle.LiveData
import androidx.lifecycle.Transformations
import com.rootdown.dev.paging_v3_1.api.DatabaseCoordinates
import com.rootdown.dev.paging_v3_1.api.RootDownService
import com.rootdown.dev.paging_v3_1.api.asDatabaseModel
import com.rootdown.dev.paging_v3_1.data.asDomainModel
import com.rootdown.dev.paging_v3_1.db.RepoDatabase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject

class MapsRepo @Inject constructor(
    private val service: RootDownService,
    private val database: RepoDatabase,
) {
    suspend fun getCoordinates() {
            withContext(Dispatchers.IO) {
                val latlng = service.getLatLng()
                database.dbLatLng().insertAll(latlng.asDatabaseModel())
            }
    }
    val latlng: LiveData<List<DatabaseCoordinates>> = Transformations.map(database.dbLatLng().getLatLngLs()){
        it.asDomainModel()
    }
}