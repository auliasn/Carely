package com.example.carely

object ObatManager {
    val obatList = mutableListOf<Obat>()

    fun setSudahDiminum(id: Int) {
        val obat = obatList.find { it.id == id }
        if (obat != null) {
            val index = obatList.indexOf(obat)
            obatList[index] = obat.copy(status = statusObat.SUDAH_DIMINUM)
        }
    }

    fun getObat(id: Int): Obat? {
        return obatList.find { it.id == id }
    }
}
