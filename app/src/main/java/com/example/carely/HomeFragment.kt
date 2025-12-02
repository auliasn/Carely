package com.example.carely

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.navigation.fragment.findNavController
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

        val textViewMessage = view.findViewById<TextView>(R.id.textViewMessage)
        textViewMessage.text = "DAILY MEDS"

        // === DATA OBAT (ini dulu hilang, jadi aku tambahkan) ===
        val listObat = listOf(
            Obat(1,"Vitamin C", "1 Tablet", 7, 0, "-", statusObat.SUDAH_DIMINUM),
            Obat(2,"Omeprazole", "1 Kapsul", 6, 30, "Sebelum Makan", statusObat.BELUM_DIMINUM),
            Obat(3,"Simvastatin", "1 Tablet", 21, 0, "Setelah Makan", statusObat.BELUM_DIMINUM),
            Obat(4,"Kalsium D3", "1 Tablet", 20, 30, "Setelah Makan", statusObat.BELUM_DIMINUM),
            Obat(5,"Amoxilin", "1 Tablet", 20, 30, "Setelah Makan", statusObat.BELUM_DIMINUM),
            Obat(6,"Ibuprofen", "1 Tablet", 7, 0, "Setelah Makan", statusObat.BELUM_DIMINUM)
        )

        val recyclerViewMeds = view.findViewById<RecyclerView>(R.id.recyclerViewMeds)
        recyclerViewMeds.layoutManager = GridLayoutManager(requireContext(), 2)

        // === ADAPTER DENGAN KLIK ITEM (untuk ke halaman edit) ===
        val adapter = ObatAdapter(listObat) { obat ->
            val bundle = Bundle().apply {
                putString("name", obat.name)
                putString("dose", obat.dose)
                putString("time", obat.time)
                putString("note", obat.note)
            }

            findNavController().navigate(
                R.id.action_homeFragment_to_editObatFragment,
                bundle
            )
        }
        val btnAdd: ImageView = view.findViewById(R.id.btnAdd)

        btnAdd.setOnClickListener {
            findNavController().navigate(R.id.action_homeFragment_to_addFragment)
        }

        recyclerViewMeds.adapter = adapter
    }
}