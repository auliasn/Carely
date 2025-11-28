package com.example.carely

import android.content.res.ColorStateList
import android.graphics.Color
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView

class HomeFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_home, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // ====== AMBIL USERNAME ======
        val username = requireActivity()
            .intent
            .getStringExtra(LoginActivity.KEY_USERNAME)

        val textViewMessage = view.findViewById<TextView>(R.id.textViewMessage)
        textViewMessage.text = "Hello $username"

        // ====== RECYCLER VIEW ======
        val recyclerViewMeds = view.findViewById<RecyclerView>(R.id.recyclerViewMeds)
        recyclerViewMeds.layoutManager = GridLayoutManager(requireContext(), 2)

        val listObat = listOf(
            Obat("Vitamin C", "1 Tablet", "07.00", "-", statusObat.BELUM_DIMINUM),
            Obat("Omeprazole", "1 Kapsul", "06.30", "Sebelum Makan", statusObat.BELUM_DIMINUM),
            Obat("Simvastatin", "1 Tablet", "21.00", "Setelah Makan", statusObat.BELUM_DIMINUM),
            Obat("Kalsium D3", "1 Tablet Kunyah", "20.30", "Setelah Makan", statusObat.BELUM_DIMINUM),
            Obat("Amoxilin", "1 Tablet", "20.30", "Setelah Makan", statusObat.BELUM_DIMINUM),
            Obat("Ibuprofen", "1 Tablet", "07.00", "Setelah Makan", statusObat.BELUM_DIMINUM),
            Obat("Ibuprofen", "1 Tablet", "07.00", "Setelah Makan", statusObat.BELUM_DIMINUM)
        )

        val adapter = ObatAdapter(listObat)
        recyclerViewMeds.adapter = adapter
    }
}