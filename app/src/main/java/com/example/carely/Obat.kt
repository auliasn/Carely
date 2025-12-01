package com.example.carely

data class Obat (
    val id : Int,
    val name : String,
    val dose : String,
    val hour: Int,
    val minute: Int,
    val note : String? = "-",
    val status : statusObat = statusObat.BELUM_DIMINUM
)

enum class statusObat {
    SUDAH_DIMINUM,
    BELUM_DIMINUM
}
