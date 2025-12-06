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

    private lateinit var listObat: MutableList<Obat>
    private lateinit var adapter: ObatAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_home, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val textViewMessage = view.findViewById<TextView>(R.id.tvHistory)
        textViewMessage.text = "DAILY MEDS"

        // 1️⃣ LOAD DATA DARI MEMORY
        listObat = ObatStorage.load(requireContext())

        // Jika pertama kali app, isi default
        if (listObat.isEmpty()) {
            listObat = mutableListOf(
                Obat(1,"Vitamin C", "1 Tablet", 7, 0, "-", statusObat.SUDAH_DIMINUM),
                Obat(2,"Omeprazole", "1 Kapsul", 6, 30, "Sebelum Makan", statusObat.BELUM_DIMINUM),
                Obat(3,"Simvastatin", "1 Tablet", 21, 0, "Setelah Makan", statusObat.BELUM_DIMINUM),
                Obat(4,"Kalsium D3", "1 Tablet", 20, 30, "Setelah Makan", statusObat.BELUM_DIMINUM),
                Obat(5,"Amoxilin", "1 Tablet", 20, 30, "Setelah Makan", statusObat.BELUM_DIMINUM),
                Obat(6,"Ibuprofen", "1 Tablet", 7, 0, "Setelah Makan", statusObat.BELUM_DIMINUM)
            )
            ObatStorage.save(requireContext(), listObat)
        }

        val recyclerView = view.findViewById<RecyclerView>(R.id.recyclerViewMeds)
        recyclerView.layoutManager = GridLayoutManager(requireContext(), 2)

        adapter = ObatAdapter(listObat) { obat ->
            val bundle = Bundle().apply {
                putInt("id", obat.id)
                putString("nama", obat.name)
                putString("dosis", obat.dose)
                putString("waktu", "${obat.hour}:${obat.minute}")
                putString("catatan", obat.note)
            }
            findNavController().navigate(
                R.id.action_homeFragment_to_editObatFragment,
                bundle
            )
        }

        recyclerView.adapter = adapter

        // 2️⃣ TERIMA DATA TAMBAH OBAT
        parentFragmentManager.setFragmentResultListener("addObatResult", viewLifecycleOwner) { _, bundle ->

            val newObat = Obat(
                id = (listObat.maxOfOrNull { it.id } ?: 0) + 1,
                name = bundle.getString("nama", "-"),
                dose = bundle.getString("dosis", "-"),
                hour = bundle.getInt("hour"),
                minute = bundle.getInt("minute"),
                note = bundle.getString("catatan", "-"),
                status = statusObat.BELUM_DIMINUM
            )

            listObat.add(newObat)
            adapter.notifyItemInserted(listObat.size - 1)

            // SAVE
            ObatStorage.save(requireContext(), listObat)
        }

        // 3️⃣ TERIMA DATA EDIT / DELETE
        parentFragmentManager.setFragmentResultListener("editObatResult", viewLifecycleOwner) { _, bundle ->

            val action = bundle.getString("action") ?: return@setFragmentResultListener
            val id = bundle.getInt("id")

            val index = listObat.indexOfFirst { it.id == id }
            if (index == -1) return@setFragmentResultListener

            if (action == "update") {
                val waktu = bundle.getString("waktu")?.split(":")

                listObat[index] = listObat[index].copy(
                    name = bundle.getString("nama", listObat[index].name),
                    dose = bundle.getString("dosis", listObat[index].dose),
                    hour = waktu?.getOrNull(0)?.toIntOrNull() ?: listObat[index].hour,
                    minute = waktu?.getOrNull(1)?.toIntOrNull() ?: listObat[index].minute,
                    note = bundle.getString("catatan", listObat[index].note)
                )

                adapter.notifyItemChanged(index)
            }

            if (action == "delete") {
                listObat.removeAt(index)
                adapter.notifyItemRemoved(index)
            }

            // SAVE
            ObatStorage.save(requireContext(), listObat)
        }

        // 4️⃣ TOMBOL ADD
        view.findViewById<ImageView>(R.id.btnAdd).setOnClickListener {
            findNavController().navigate(R.id.action_homeFragment_to_addFragment)
        }
    }
    override fun onResume() {
        super.onResume()

        // reload data terbaru dari file (ObatStorage)
        listObat = ObatStorage.load(requireContext())

        // refresh adapter supaya border berubah
        adapter.notifyDataSetChanged()
    }
}