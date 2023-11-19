package com.rootdown.dev.paging_v3_1.repo

import androidx.lifecycle.LiveData
import androidx.lifecycle.Transformations
import com.rootdown.dev.paging_v3_1.api.ApiServiceStrain
import com.rootdown.dev.paging_v3_1.api.Strain
import com.rootdown.dev.paging_v3_1.api.asDatabaseModel
import com.rootdown.dev.paging_v3_1.data.DatabaseStrain
import com.rootdown.dev.paging_v3_1.data.asDomainModel
import com.rootdown.dev.paging_v3_1.db.RepoDatabase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.withContext
import javax.inject.Inject


class StrainsRepository @Inject constructor(
    private val service: ApiServiceStrain,
    private val database: RepoDatabase,
) {
    suspend fun refreshStrains() {
            withContext(Dispatchers.IO) {
                val strainlist = service.getStrainsList()
                database.strainDao().insertAll(strainlist.asDatabaseModel())
            }
    }

    val strain: LiveData<List<Strain>> = Transformations.map(database.strainDao().getStrains()){
        it.asDomainModel()
    }

    fun getStrain(strainId: String?): LiveData<DatabaseStrain> {
        return database.strainDao().strainById(strainId)
    }


    fun getSearchResultStream(query: String): Flow<List<DatabaseStrain>> {
        return database.strainDao().strainByType(query)
    }
}