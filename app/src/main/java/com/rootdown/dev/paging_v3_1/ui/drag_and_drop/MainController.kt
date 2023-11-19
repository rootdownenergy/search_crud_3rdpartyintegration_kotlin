package com.rootdown.dev.paging_v3_1.ui.drag_and_drop

import android.view.MotionEvent
import android.view.View
import com.airbnb.epoxy.EpoxyController
import com.rootdown.dev.paging_v3_1.ui.drag_and_drop.epoxymodels.row

class MainController(
    private val callbackAdapter: MainCallbackAdapter
) : EpoxyController() {

    private var firstRows = emptyList<ReorderViewModel.Row>()
    //private var secondRows = emptyList<ReorderViewModel.Row>()

    override fun buildModels() {

        firstRows.map { row ->
            row {
                id(row.id)
                rowId(row.id)
                title(row.title)
                onDragHandleTouchListener(this@MainController::onRowDragHandleTouched)
            }
        }

    }

    fun setFirstRowsData(firstRowsData: List<ReorderViewModel.Row>) {
        this.firstRows = firstRowsData
        requestModelBuild()
    }

    private fun onRowDragHandleTouched(view: View, event: MotionEvent): Boolean {
        if (event.action == MotionEvent.ACTION_DOWN) {
            callbackAdapter.onDragStart()
        }

        return view.performClick()
    }

}