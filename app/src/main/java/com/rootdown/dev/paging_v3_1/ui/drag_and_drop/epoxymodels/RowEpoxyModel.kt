package com.rootdown.dev.paging_v3_1.ui.drag_and_drop.epoxymodels

import android.annotation.SuppressLint
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import com.airbnb.epoxy.EpoxyAttribute
import com.airbnb.epoxy.EpoxyModelClass
import com.airbnb.epoxy.EpoxyModelWithHolder
import com.rootdown.dev.paging_v3_1.R
import com.rootdown.dev.paging_v3_1.ui.drag_and_drop.helpers.KotlinEpoxyHolder

@SuppressLint("NonConstantResourceId")
@EpoxyModelClass(layout = R.layout.item_model_row)
abstract class RowEpoxyModel : EpoxyModelWithHolder<RowEpoxyModel.RowViewHolder>() {

    @EpoxyAttribute
    lateinit var rowId: String
    @EpoxyAttribute lateinit var title: String
    @EpoxyAttribute var onDragHandleTouchListener: View.OnTouchListener? = null

    @SuppressLint("ClickableViewAccessibility")
    override fun bind(holder: RowViewHolder) {
        with(holder) {
            tvTitle.text = title
            ivDragHandle.setOnTouchListener(onDragHandleTouchListener)
        }
    }

    @SuppressLint("ClickableViewAccessibility")
    override fun unbind(holder: RowViewHolder) {
        with(holder) {
            ivDragHandle.setOnTouchListener(onDragHandleTouchListener)
        }
    }

    class RowViewHolder : KotlinEpoxyHolder() {
        val tvTitle by bind<TextView>(R.id.tv_title)
        val ivDragHandle by bind<ImageView>(R.id.iv_drag_handle)
    }

}