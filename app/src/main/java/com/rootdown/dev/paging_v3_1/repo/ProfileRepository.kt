package com.rootdown.dev.paging_v3_1.repo

import androidx.lifecycle.LiveData
import com.rootdown.dev.paging_v3_1.db.RepoDatabase
import com.rootdown.dev.paging_v3_1.data.Repo
import javax.inject.Inject

class ProfileRepository @Inject constructor(
    private val database: RepoDatabase,
){

    val profile_ls: LiveData<List<Repo>> = database.reposDao().getprofiles()
    fun getProfile(profileId: String?): LiveData<Repo> {
        return database.reposDao().getProfileById(profileId)
    }

}