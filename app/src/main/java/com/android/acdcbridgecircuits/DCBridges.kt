package com.android.acdcbridgecircuits

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class DCBridges (var picture: Int?,
                      var name: String?,
                      var detail: String,
                      var equation: Int?
) : Parcelable
