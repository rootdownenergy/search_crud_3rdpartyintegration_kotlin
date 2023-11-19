package com.rootdown.dev.paging_v3_1.ui.geomapper

import android.view.View
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import com.rootdown.dev.paging_v3_1.R

class ContentUserIconsViewHolder(view: View, private val onItemClicked: (position: Int) -> Unit): RecyclerView.ViewHolder(view), View.OnClickListener {
    var imageView: ImageView = view.findViewById(R.id.content_user_img)

    override fun onClick(v: View) {
        val position = absoluteAdapterPosition
        onItemClicked(position)
    }
}