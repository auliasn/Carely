package com.example.carely

import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

data class Obat (
    val name : String,
    val dose : String,
    val time : String = getCurrentTime(),
    val note : String? = "-"
)

fun getCurrentTime(): String {
    val sdf = SimpleDateFormat("HH.mm WIB", Locale.getDefault())
    return sdf.format(Date())
}
