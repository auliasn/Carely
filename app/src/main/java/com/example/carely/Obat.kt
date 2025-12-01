package com.example.carely

import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

data class Obat (
    val id : Int,
    val name : String,
    val dose : String,
    val time : String = getCurrentTime(),
    val note : String? = "-",
    val status : statusObat = statusObat.BELUM_DIMINUM
)

enum class statusObat {
    SUDAH_DIMINUM,
    BELUM_DIMINUM
}

fun getCurrentTime(): String {
    val sdf = SimpleDateFormat("HH.mm WIB", Locale.getDefault())
    return sdf.format(Date())
}
