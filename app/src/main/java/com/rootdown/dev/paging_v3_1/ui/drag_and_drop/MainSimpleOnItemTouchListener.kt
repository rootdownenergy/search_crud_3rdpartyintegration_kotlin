package com.rootdown.dev.paging_v3_1.ui.drag_and_drop

import android.view.MotionEvent
import androidx.recyclerview.widget.RecyclerView

class MainSimpleOnItemTouchListener(
    private val listener: OnInterceptTouchEventListener
) : RecyclerView.SimpleOnItemTouchListener() {

    interface OnInterceptTouchEventListener {

        fun onInterceptTouchEvent(touchedPosition: Int)

    }

    override fun onInterceptTouchEvent(recyclerView: RecyclerView, event: MotionEvent): Boolean {
        val childView = recyclerView.findChildViewUnder(event.x, event.y)
        val touchedPosition = childView?.let { recyclerView.getChildAdapterPosition(childView) } ?: -1
        listener.onInterceptTouchEvent(touchedPosition)

        return false
    }

}