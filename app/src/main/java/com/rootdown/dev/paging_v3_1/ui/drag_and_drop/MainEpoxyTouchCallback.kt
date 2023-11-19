package com.rootdown.dev.paging_v3_1.ui.drag_and_drop

import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.RecyclerView
import com.airbnb.epoxy.EpoxyModelTouchCallback
import com.airbnb.epoxy.EpoxyViewHolder
import com.rootdown.dev.paging_v3_1.ui.drag_and_drop.epoxymodels.RowEpoxyModel

class MainEpoxyTouchCallback(
    controller: MainController,
    private val listener: OnRowMoveListener
) : EpoxyModelTouchCallback<RowEpoxyModel>(controller, RowEpoxyModel::class.java) {

    interface OnRowMoveListener {

        fun onMoved(movingRowId: String, shiftingRowId: String)

    }

    override fun onMove(
        recyclerView: RecyclerView,
        viewHolder: EpoxyViewHolder,
        target: EpoxyViewHolder
    ): Boolean {
        val movingRowId = viewHolder?.model
            ?.takeIf { it is RowEpoxyModel }
            ?.let { (it as RowEpoxyModel).rowId }
        val shiftingRowId = target?.model
            ?.takeIf { it is RowEpoxyModel }
            ?.let { (it as RowEpoxyModel).rowId }

        if (movingRowId != null && shiftingRowId != null) {
            listener.onMoved(movingRowId, shiftingRowId)
        }
        return super.onMove(recyclerView, viewHolder, target)
    }



    override fun getMovementFlagsForModel(model: RowEpoxyModel?, adapterPosition: Int) =
        makeMovementFlags(ItemTouchHelper.UP or ItemTouchHelper.DOWN, 0)

    override fun isLongPressDragEnabled() = false

}