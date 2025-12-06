package com.example.carely

import android.app.TimePickerDialog
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.ImageView
import android.widget.Toast
import java.sql.Time

class AddFragment : Fragment() {


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_add, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // Ambil view dari layout
        val btnBack: ImageView = view.findViewById(R.id.btnBack)
        val etName: EditText = view.findViewById(R.id.addName)
        val etTime: EditText = view.findViewById(R.id.addTime)
        val etDose: EditText = view.findViewById(R.id.addDose)
        val etNote: EditText = view.findViewById(R.id.addNote)
        val btnSave: Button = view.findViewById(R.id.btnSave)

        etTime.isFocusable = false
        etTime.isClickable = true

        etTime.setOnClickListener {
            val timeValue = etTime.text.toString()
            val parts = timeValue.split(":")
            val currentHour = parts.toString().substringBefore(":").toIntOrNull() ?: 0
            val currentMinute = parts.toString().substringBefore(":").toIntOrNull() ?: 0

            TimePickerDialog(
                requireContext(),
                { _, hourOfDay, minute ->
                    val formatted = "%02d:%02d".format(hourOfDay, minute)
                    etTime.setText(formatted)
                },
                currentHour,
                currentMinute,
                true
            ).show()
        }

        // Tombol back
        btnBack.setOnClickListener {
            requireActivity().onBackPressedDispatcher.onBackPressed()
        }

        // Tombol save
        btnSave.setOnClickListener {
            val name = etName.text.toString().trim()
            val dose = etDose.text.toString().trim()
            val time = etTime.text.toString().trim()
            val note = etNote.text.toString().trim()

            if (name.isEmpty() || dose.isEmpty() || time.isEmpty()) {
                Toast.makeText(requireContext(), "Semua data wajib diisi", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            val bundle = Bundle().apply {
                putString("nama", name)
                putString("dosis", dose)
                putString("catatan", if (note.isEmpty()) "-" else note)
                putString("waktuFormat", time)
            }

            parentFragmentManager.setFragmentResult("addObatResult", bundle)
            requireActivity().onBackPressedDispatcher.onBackPressed()
        }
    }
}