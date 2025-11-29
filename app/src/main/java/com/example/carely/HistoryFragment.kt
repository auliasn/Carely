package com.example.carely

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

class HistoryFragment : Fragment() {

    // Fungsi ini dipanggil waktu fragment mau dibuat
    override fun onCreateView(
        inflater: LayoutInflater,     // ini buat "ngembangin" layout (membaca XML jadi tampilan)
        container: ViewGroup?,        // tempat fragment bakal ditaruh
        savedInstanceState: Bundle?   // data sebelum-sebelumnya (biasanya ga dipakai)
    ): View? {

        // DI SINI KITA NAMPILIN XML-nya
        // fragment_history = nama file layout XML kamu
        val view = inflater.inflate(R.layout.fragment_history, container, false)

        // mengembalikan tampilan tadi supaya ditampilkan di layar
        return view
    }
}
