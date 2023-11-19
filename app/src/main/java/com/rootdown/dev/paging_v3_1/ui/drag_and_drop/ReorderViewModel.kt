package com.rootdown.dev.paging_v3_1.ui.drag_and_drop

import android.app.Application
import android.util.Log
import androidx.lifecycle.*
import com.rootdown.dev.paging_v3_1.api.UserStrain
import com.rootdown.dev.paging_v3_1.db.RepoDatabase
import com.rootdown.dev.paging_v3_1.repo.UserContent
import dagger.hilt.android.lifecycle.HiltViewModel
import java.util.*
import javax.inject.Inject
import kotlin.math.floor


@HiltViewModel
class ReorderViewModel @Inject constructor(
    private val repo: UserContent,
) : ViewModel() {

    private val userContent = repo
    private val mutexStrainList = MutableLiveData<MutableList<UserStrain>>()
    val updatedStrains: LiveData<List<UserStrain>> = userContent.userStrains

    val mediator = MediatorLiveData<Unit>()

    private val rowsData = MutableLiveData<MutableList<Row>>()
    val firstRowsData: LiveData<MutableList<Row>> = Transformations.map(rowsData) {
        Log.w("floor$$", "${it.size}")
        it.subList(0, floor((it.size / 2).toDouble()).toInt())
    }

    init {
        rowsData.value = MutableList(40) { Row(id = UUID.randomUUID().toString(), title = "#:$it") }
    }



    fun moveRow(movingRowId: String, shiftingRowId: String) {
        val movingRow = rowsData.value?.find { it.id == movingRowId }
        val shiftingRow = rowsData.value?.find { it.id == shiftingRowId }

        if (movingRow != null && shiftingRow != null) {
            rowsData.value
                ?.indexOf(shiftingRow)
                ?.let { moveRowToPosition(it, movingRow) }
        }
    }

    private fun moveRowToPosition(position: Int, movingRow: Row) {
        rowsData.value?.remove(movingRow)
        rowsData.value?.add(position, movingRow)
        rowsData.notifyObserver()
    }

    private fun <T> MutableLiveData<T>.notifyObserver() {
        this.value = this.value
    }

    data class Row(
        val id: String,
        val title: String
    )

    data class UserStrainMutex(
        val id: String,
        val name: String?,
    )


    val mutexList = mutableListOf<UserStrainMutex>(
        UserStrainMutex(
            id = UUID.randomUUID().toString(),
            name = "Afgoeey"
        ),
        UserStrainMutex(
            id = UUID.randomUUID().toString(),
            name = "African Queen"
        ),
        UserStrainMutex(
            id = UUID.randomUUID().toString(),
            name = "big Skunk"
        ),
        UserStrainMutex(
            id = UUID.randomUUID().toString(),
            name = "Brainstorm Haze"
        ),
        UserStrainMutex(
            id = UUID.randomUUID().toString(),
            name = "Chitral"
        ),
    )

}