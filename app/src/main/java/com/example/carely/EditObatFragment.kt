package com.example.carely

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText

class EditObatFragment : Fragment() {

    private lateinit var editName: EditText
    private lateinit var editDose: EditText
    private lateinit var editHour: EditText
    private lateinit var editMinute: EditText
    private lateinit var editNote: EditText

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_edit_obat, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // Ambil referensi EditText
        editName = view.findViewById(R.id.editName)
        editDose = view.findViewById(R.id.editDose)
        editHour = view.findViewById(R.id.editHour)
        editMinute = view.findViewById(R.id.editMinute)
        editNote = view.findViewById(R.id.editNote)

        // Ambil data dari HomeFragment
        val name = arguments?.getString("nama") ?: ""
        val dose = arguments?.getString("dosis") ?: ""
        val time = arguments?.getString("waktu") ?: ""
        val note = arguments?.getString("catatan") ?: ""

        if (time.contains(":")) {
            val split = time.split(":")
            editHour.setText(split[0])
            editMinute.setText(split[1])
        }

        // Tampilkan ke EditText
        editName.setText(name)
        editDose.setText(dose)
        editNote.setText(note)
    }
}