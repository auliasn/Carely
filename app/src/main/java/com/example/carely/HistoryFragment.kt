package com.example.carely

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class HistoryFragment : Fragment() {

    // Fungsi untuk memanggil fragment yg mau dibuat
    override fun onCreateView(
        inflater: LayoutInflater,     // mengembangkan layout (membaca XML jadi tampilan)
        container: ViewGroup?,        // tempat fragment
        savedInstanceState: Bundle?   // data sebelum-sebelumnya
    ): View? {

        // tampilan XML-nya

        val view = inflater.inflate(R.layout.fragment_history, container, false)

        val recycler = view.findViewById<RecyclerView>(R.id.recyclerHistory)
        recycler.layoutManager = LinearLayoutManager(requireContext())
        recycler.adapter = HistoryAdapter(HistoryManager.getAllHistory())

        // mengembalikan tampilan agar ditampilkan di layar
        return view
    }
}
