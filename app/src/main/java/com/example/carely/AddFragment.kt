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
import androidx.navigation.fragment.findNavController
import com.google.android.material.bottomnavigation.BottomNavigationView
import java.sql.Time

class AddFragment : Fragment() {

    private var selectedHour = -1
    private var selectedMinute = -1

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_add, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        activity?.findViewById<BottomNavigationView>(R.id.bottomNavigationMenu)?.visibility = View.GONE

        // Ambil view dari layout
        val btnBack: ImageView = view.findViewById(R.id.btnBack)
        val etName: EditText = view.findViewById(R.id.addName)
        val etTime: EditText = view.findViewById(R.id.addTime)
        val etDose: EditText = view.findViewById(R.id.addDose)
        val etNote: EditText = view.findViewById(R.id.addNote)
        val btnSave: Button = view.findViewById(R.id.btnSave)

        etTime.isFocusable = false
        etTime.isClickable = true

        val now = java.util.Calendar.getInstance()
        val currentHour = now.get(java.util.Calendar.HOUR_OF_DAY)
        val currentMinute = now.get(java.util.Calendar.MINUTE)

        etTime.setOnClickListener {
            TimePickerDialog(
                requireContext(),
                { _, hourOfDay, minute ->
                    selectedHour = hourOfDay
                    selectedMinute = minute
                    etTime.setText("%02d:%02d".format(hourOfDay, minute))
                },
                currentHour,
                currentMinute,
                true
            ).show()
        }

        // Tombol back
        btnBack.setOnClickListener {
            findNavController().navigateUp()
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

            if (selectedHour == -1 || selectedMinute == -1) {
                Toast.makeText(requireContext(), "Silakan pilih waktu terlebih dahulu", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            val bundle = Bundle().apply {
                putString("nama", name)
                putString("dosis", dose)
                putString("catatan", if (note.isEmpty()) "-" else note)
                putString("waktuFormat", time)

                // Tambahan penting
                putInt("hour", selectedHour)
                putInt("minute", selectedMinute)
            }

            val newId = (System.currentTimeMillis() % Int.MAX_VALUE).toInt()

            AlarmHelper.setAlarm(
                requireContext(),
                id = newId,
                hour = selectedHour,
                minute = selectedMinute,
                nama = name
            )

            parentFragmentManager.setFragmentResult("addObatResult", bundle)
            findNavController().navigateUp()
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        activity?.findViewById<BottomNavigationView>(R.id.bottomNavigationMenu)?.visibility = View.VISIBLE
    }
}