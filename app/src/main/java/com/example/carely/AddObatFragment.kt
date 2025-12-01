package com.example.carely

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.ImageView
import android.widget.Toast

class AddObatFragment : Fragment() {


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_add_obat, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // Ambil view dari layout
        val btnBack: ImageView = view.findViewById(R.id.btnBack)
        val etName: EditText = view.findViewById(R.id.addName)
        val etHour: EditText = view.findViewById(R.id.addHour)
        val etMinute: EditText = view.findViewById(R.id.addMinute)
        val etDose: EditText = view.findViewById(R.id.addDose)
        val etNote: EditText = view.findViewById(R.id.addNote)
        val btnSave: Button = view.findViewById(R.id.btnSave)

        // Tombol back
        btnBack.setOnClickListener {
            requireActivity().onBackPressedDispatcher.onBackPressed()
        }

        // Tombol save
        btnSave.setOnClickListener {
            val name = etName.text.toString().trim()
            val dose = etDose.text.toString().trim()
            val hour = etHour.text.toString().trim()
            val minute = etMinute.text.toString().trim()
            val note = etNote.text.toString().trim()

            // Validasi basic
            if (name.isEmpty() || dose.isEmpty() || hour.isEmpty() || minute.isEmpty()) {
                Toast.makeText(requireContext(), "Semua data wajib diisi", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            val hourInt = hour.toIntOrNull() ?: 0
            val minuteInt = minute.toIntOrNull() ?: 0

            val obatBaru = Obat(
                id = 0, // kalau pakai Room biasanya auto-generate
                name = name,
                dose = dose,
                hour = hourInt,
                minute = minuteInt,
                note = if (note.isEmpty()) "-" else note,
                status = statusObat.BELUM_DIMINUM
            )

            // TODO: di sini nanti kamu simpan ke DB / kirim ke ViewModel
            // contoh nanti:
            // viewModel.insert(obatBaru)

            Toast.makeText(requireContext(), "Obat berhasil ditambahkan", Toast.LENGTH_SHORT).show()

            // Balik ke sebelumnya
            requireActivity().onBackPressedDispatcher.onBackPressed()
        }
    }
}