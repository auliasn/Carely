package com.example.carely

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.PopupMenu
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class ObatAdapter (val listObat : List<Obat>) : RecyclerView.Adapter<ObatAdapter.ObatViewHolder>(){
    class ObatViewHolder (val row: View) : RecyclerView.ViewHolder(row){
        val textViewName = row.findViewById<TextView>(R.id.textViewName)
        val textViewDose = row.findViewById<TextView>(R.id.textViewDose)
        val textViewTime = row.findViewById<TextView>(R.id.textViewTime)
        val textViewNote = row.findViewById<TextView>(R.id.textViewNote)
        val btnMore = row.findViewById<ImageView>(R.id.btnMore)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ObatViewHolder {
        val layout =
            LayoutInflater.from(parent.context).inflate(
                R.layout.item_obat,
                parent,
                false
            )
        return ObatViewHolder(layout)
    }

    override fun getItemCount(): Int = listObat.size

    override fun onBindViewHolder(holder: ObatViewHolder, position: Int) {
        val obat = listObat[position]

        holder.textViewName.text = obat.name
        holder.textViewDose.text = obat.dose
        holder.textViewTime.text = obat.time
        holder.textViewNote.text = obat.note

        holder.btnMore.setOnClickListener { view ->
            val popup = PopupMenu(view.context, view)
            popup.inflate(R.menu.options_menu)

            try {
                val fieldMPopup = PopupMenu::class.java.getDeclaredField("mPopup")
                fieldMPopup.isAccessible = true
                val mPopup = fieldMPopup.get(popup)
                mPopup.javaClass
                    .getDeclaredMethod("setForceShowIcon", Boolean::class.java)
                    .invoke(mPopup, true)
            } catch (e: Exception) {
                e.printStackTrace()
            }

            popup.setOnMenuItemClickListener { item ->
                when (item.itemId) {
                    R.id.menu_edit -> {
                        true
                    }
                    else -> false
                }
            }
            popup.show()
        }
    }
}