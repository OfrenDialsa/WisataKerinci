package com.example.wisatakerinci

import android.os.Parcelable
import kotlinx.parcelize.Parcelize


@Parcelize
data class Destinasi(
    val title : String,
    val desc : String,
    val photo: Int,
    val lokasi: String,
    val kodePos: String

) : Parcelable
