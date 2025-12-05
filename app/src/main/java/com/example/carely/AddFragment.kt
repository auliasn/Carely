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

class AddFragment : Fragment() {


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_add, container, false)
        return view
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

            if (name.isEmpty() || dose.isEmpty() || hour.isEmpty() || minute.isEmpty()) {
                Toast.makeText(requireContext(), "Semua data wajib diisi", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            // Validasi angka
            val hourInt = hour.toIntOrNull()
            val minuteInt = minute.toIntOrNull()

            if (hourInt == null || minuteInt == null || hourInt !in 0..23 || minuteInt !in 0..59) {
                Toast.makeText(requireContext(), "Waktu tidak valid", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            // Format dua digit
            val hourFormatted = String.format("%02d", hourInt)
            val minuteFormatted = String.format("%02d", minuteInt)

            val bundle = Bundle().apply {
                putString("nama", name)
                putString("dosis", dose)
                putInt("hour", hour.toInt())
                putInt("minute", minute.toInt())
                putString("catatan", if (note.isEmpty()) "-" else note)
                putString("waktuFormat", "$hourFormatted:$minuteFormatted")
            }

            parentFragmentManager.setFragmentResult("addObatResult", bundle)

            requireActivity().onBackPressedDispatcher.onBackPressed()
        }

    }
}