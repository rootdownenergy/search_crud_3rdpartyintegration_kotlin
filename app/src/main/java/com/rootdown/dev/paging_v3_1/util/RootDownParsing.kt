package com.rootdown.dev.paging_v3_1.util

import android.os.Parcel
import android.os.Parcelable

class RootDownParsing() : Parcelable {

    var count = 0
    var propertyWithCounter: Int? = null
    constructor(parcel: Parcel) : this() {
    }
    val arr: ArrayList<Int> = arrayListOf(2, 3, 6)

    val arrt: ArrayList<Int> = arrayListOf(2, 7, 8)

    override fun writeToParcel(parcel: Parcel, flags: Int) {

    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<RootDownParsing> {
        override fun createFromParcel(parcel: Parcel): RootDownParsing {
            return RootDownParsing(parcel)
        }

        override fun newArray(size: Int): Array<RootDownParsing?> {
            return arrayOfNulls(size)
        }
    }
}