package com.rootdown.dev.paging_v3_1.model

import androidx.annotation.Keep
import androidx.room.Entity
import com.google.gson.annotations.SerializedName

@Keep
@Entity(tableName = "meta")
data class Meta(
    @field:SerializedName("current_page") val current_page: Int,
    @field:SerializedName("from") val from: Int,
    @field:SerializedName("last_page") val last_page: Int,
    @field:SerializedName("path") val path: String,
    @field:SerializedName("per_page") val per_page: String,
    @field:SerializedName("to") val to: String,
    @field:SerializedName("total") val total: Int
)