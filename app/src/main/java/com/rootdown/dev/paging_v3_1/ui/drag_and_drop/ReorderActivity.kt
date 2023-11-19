package com.rootdown.dev.paging_v3_1.ui.drag_and_drop

import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.rootdown.dev.paging_v3_1.R
import com.rootdown.dev.paging_v3_1.databinding.ActivityReorderBinding
import com.rootdown.dev.paging_v3_1.ui.drag_and_drop.epoxymodels.RowEpoxyModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class ReorderActivity : AppCompatActivity(R.layout.activity_reorder),
MainCallbackAdapter, MainSimpleOnItemTouchListener.OnInterceptTouchEventListener,
    MainEpoxyTouchCallback.OnRowMoveListener {

    private lateinit var binding: ActivityReorderBinding
    private val viewModel: ReorderViewModel by viewModels()
    private val controller = MainController(this)
    private var touchHelper: ItemTouchHelper? = null
    private var touchedPosition = -1

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityReorderBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)
        setupList()
        observeRowsData()
    }

    /** @see MainCallbackAdapter */

    override fun onDragStart() {
        if (touchedPosition >= 0) {
            val touchedModel = controller.adapter.getModelAtPosition(touchedPosition)
            if (touchedModel is RowEpoxyModel) {
                val touchedViewHolder = controller.adapter.boundViewHolders.getHolderForModel(touchedModel)
                if (touchedViewHolder != null) {
                    touchHelper?.startDrag(touchedViewHolder)
                }
            }
        }
    }

    /** @see OnInterceptTouchEventListener */

    override fun onInterceptTouchEvent(touchedPosition: Int) {
        this.touchedPosition = touchedPosition
    }

    /** @see OnRowMoveListener */

    override fun onMoved(movingRowId: String, shiftingRowId: String) {
        viewModel.moveRow(movingRowId, shiftingRowId)
    }

    private fun setupList() {
        val layoutManager = LinearLayoutManager(this)

        binding.ervList.layoutManager = layoutManager
        binding.ervList.setControllerAndBuildModels(controller)
        binding.ervList.addOnItemTouchListener(MainSimpleOnItemTouchListener(this))
        binding.ervList.addItemDecoration(DividerItemDecoration(this, DividerItemDecoration.VERTICAL))
        setupTouchHelper(binding.ervList)
    }

    private fun setupTouchHelper(recyclerView: RecyclerView) {
        touchHelper = ItemTouchHelper(MainEpoxyTouchCallback(controller, this))
            .apply { attachToRecyclerView(recyclerView) }
    }

    private fun observeRowsData() {
        viewModel.firstRowsData.observe(this, { controller.setFirstRowsData(it) })
        //viewModel.secondRowsData.observe(this, { controller.setSecondRowsData(it) })
    }

}