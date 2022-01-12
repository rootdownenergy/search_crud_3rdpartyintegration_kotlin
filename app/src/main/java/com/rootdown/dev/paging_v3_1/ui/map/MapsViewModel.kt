package com.rootdown.dev.paging_v3_1.ui.map

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.viewModelScope
import com.rootdown.dev.paging_v3_1.db.RepoDatabase
import com.rootdown.dev.paging_v3_1.repo.MapsRepo
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import java.io.IOException
import javax.inject.Inject

@HiltViewModel
class MapsViewModel@Inject constructor (application: Application, state: SavedStateHandle) : AndroidViewModel(application) {

    private val latlngRepo = MapsRepo(RepoDatabase.getInstance(application))

    val latlng = latlngRepo.latlng

    init {
        refreshCoordinates()
    }

    private fun refreshCoordinates(){
        viewModelScope.launch {
            try {
                latlngRepo.getCoordinates()
            } catch (networkError: IOException) {
                // Show a Toast error message and hide the progress bar.
            }

        }
    }
}