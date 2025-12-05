package com.example.carely

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
    private lateinit var editHour: EditText
    private lateinit var editMinute: EditText
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
        editHour = view.findViewById(R.id.editHour)
        editMinute = view.findViewById(R.id.editMinute)
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

        if (time.contains(":")) {
            val split = time.split(":")
            editHour.setText(split.getOrNull(0) ?: "")
            editMinute.setText(split.getOrNull(1) ?: "")
        }

        editName.setText(name)
        editDose.setText(dose)
        editNote.setText(note)

        btnBack.setOnClickListener {
            parentFragmentManager.popBackStack()
        }

        btnSave.setOnClickListener {
            val newName = editName.text.toString().trim()
            val newDose = editDose.text.toString().trim()
            val newHour = editHour.text.toString().trim()
            val newMinute = editMinute.text.toString().trim()
            val newNote = editNote.text.toString().trim()

            val newTime =
                if (newHour.isNotEmpty() && newMinute.isNotEmpty()) "$newHour:$newMinute"
                else time

            val result = Bundle().apply {
                putString("action", "update")
                putInt("id", idObat)   // ⬅ kirim id kembali
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
                putInt("id", idObat)   // ⬅ kirim id juga saat delete
            }

            parentFragmentManager.setFragmentResult("editObatResult", result)
            parentFragmentManager.popBackStack()
        }
    }
}