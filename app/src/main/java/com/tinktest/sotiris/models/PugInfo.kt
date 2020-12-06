package com.tinktest.sotiris.models

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class PugInfo(val name: String, val description: String, val imageUrl: String): Parcelable