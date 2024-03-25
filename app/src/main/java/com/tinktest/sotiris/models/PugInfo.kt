package se.onemanstudio.test.tink.models

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class PugInfo(val name: String, val description: String, val imageUrl: String): Parcelable