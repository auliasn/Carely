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

class EditObatFragment : Fragment() {

    private lateinit var editName: EditText
    private lateinit var editDose: EditText
    private lateinit var editTime: EditText
    private lateinit var editNote: EditText
    private lateinit var btnSave: Button
    private lateinit var btnDelete: Button
    private lateinit var btnBack: ImageView

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_edit_obat, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        editName = view.findViewById(R.id.editName)
        editDose = view.findViewById(R.id.editDose)
        editTime = view.findViewById(R.id.editTime)
        editNote = view.findViewById(R.id.editNote)
        btnSave = view.findViewById(R.id.btnSave)
        btnDelete = view.findViewById(R.id.btnDelete)
        btnBack = view.findViewById(R.id.btnBack)

        // ⬅ Ambil ID obat dari arguments
        val idObat = arguments?.getInt("id") ?: -1
        if (idObat == -1) return

        val name = arguments?.getString("nama") ?: ""
        val dose = arguments?.getString("dosis") ?: ""
        val time = arguments?.getString("waktu") ?: ""
        val note = arguments?.getString("catatan") ?: ""

        editName.setText(name)
        editDose.setText(dose)
        editNote.setText(note)

        if (time.contains(":")) {
            val parts = time.split(":")
            val h = parts.getOrNull(0)?.toIntOrNull() ?: 0
            val m = parts.getOrNull(1)?.toIntOrNull() ?: 0
            editTime.setText("%02d:%02d".format(h, m))
        } else {
            editTime.setText(time)
        }

        editTime.isFocusable = false
        editTime.isClickable = true

        val showTimePicker = {
            val currentParts = editTime.text.toString().split(":")
            val currentHour = time.substringBefore(":").toIntOrNull() ?: 0
            val currentMinute = time.substringBefore(":").toIntOrNull() ?: 0

            TimePickerDialog(
                requireContext(),
                { _, hourOfDay, minute ->
                    val formatted = "%02d:%02d".format(hourOfDay, minute)
                    editTime.setText(formatted)
                },
                currentHour,
                currentMinute,
                true
            ).show()
        }
        editTime.setOnClickListener { showTimePicker() }

        btnBack.setOnClickListener {
            parentFragmentManager.popBackStack()
        }

        btnSave.setOnClickListener {
            val newName = editName.text.toString().trim()
            val newDose = editDose.text.toString().trim()
            val newTime = editTime.text.toString().trim()
            val newNote = editNote.text.toString().trim()

            val result = Bundle().apply {
                putString("action", "update")
                putInt("id", idObat)
                putString("nama", newName)
                putString("dosis", newDose)
                putString("waktu", newTime)
                putString("catatan", newNote)
            }

            parentFragmentManager.setFragmentResult("editObatResult", result)
            parentFragmentManager.popBackStack()
        }

        btnDelete.setOnClickListener {
            val result = Bundle().apply {
                putString("action", "delete")
                putInt("id", idObat)
            }

            parentFragmentManager.setFragmentResult("editObatResult", result)
            parentFragmentManager.popBackStack()
        }
    }
}