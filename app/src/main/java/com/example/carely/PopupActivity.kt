package com.example.carely

import android.os.Bundle
import android.widget.Button
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import java.util.Calendar
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class PopupActivity : AppCompatActivity() {

    private var obatId = -1
    private var obatName = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_popup)

        obatId = intent.getIntExtra("ID_OBAT", -1)
        obatName = intent.getStringExtra("NAMA_OBAT") ?: "-"

        findViewById<Button>(R.id.button_yes).setOnClickListener {
            onYesClicked()
            finish()
        }
    }

    private fun onYesClicked() {
        // 1. Update status obat
        ObatManager.setSudahDiminum(obatId)

        // 2. Simpan ke history
        val now = java.util.Calendar.getInstance()

        val date = "%02d %s %04d".format(
            now.get(Calendar.DAY_OF_MONTH),
            now.getDisplayName(Calendar.MONTH, Calendar.LONG, java.util.Locale("id")),
            now.get(Calendar.YEAR)
        ).uppercase()

        val time = "%02d.%02d WIB".format(
            now.get(Calendar.HOUR_OF_DAY),
            now.get(Calendar.MINUTE)
        )

        HistoryManager.addHistory(
            History(
                id = obatId,
                name = obatName,
                date = date,
                time = time
            )
        )
    }
}

