package com.rootdown.dev.paging_v3_1.ui.geomapper

import android.content.Context
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.rootdown.dev.paging_v3_1.R
import java.io.IOException

class ContentUserIconsAdapter(private val onItemClicked: (position: Int) -> Unit):
    RecyclerView.Adapter<ContentUserIconsViewHolder>() {

    var iiLs = listOf<String>()

    private lateinit var cc: Context

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ContentUserIconsViewHolder {
        val context = parent.context
        cc = context
        val inflater = LayoutInflater.from(context)
        val view = inflater.inflate(R.layout.content_view_item, parent, false)
        return ContentUserIconsViewHolder(view, onItemClicked)
    }

    override fun onBindViewHolder(holder: ContentUserIconsViewHolder, position: Int) {
        //val assetsBitmap:Bitmap? = getBitmapFromAssets(iiLs[position])
        //holder.imageView.setImageBitmap(assetsBitmap)
        Glide.with(cc)
            .load(iiLs[position])
            .into(holder.imageView)
    }

    override fun getItemCount(): Int = iiLs.size

    // Custom method to get assets folder image as bitmap
    private fun getBitmapFromAssets(fileName: String): Bitmap? {
        return try {
            BitmapFactory.decodeFile(fileName)
        } catch (e: IOException) {
            e.printStackTrace()
            null
        }
    }
}