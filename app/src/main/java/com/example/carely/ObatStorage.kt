package com.example.carely

import android.content.Context
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

object ObatStorage {

    private const val PREF = "obat_pref"
    private const val KEY = "obat_data"

    fun save(context: Context, data: List<Obat>) {
        val json = Gson().toJson(data)
        context.getSharedPreferences(PREF, Context.MODE_PRIVATE)
            .edit()
            .putString(KEY, json)
            .apply()
    }

    fun load(context: Context): MutableList<Obat> {
        val pref = context.getSharedPreferences(PREF, Context.MODE_PRIVATE)
        val json = pref.getString(KEY, null) ?: return mutableListOf()

        val type = object : TypeToken<MutableList<Obat>>() {}.type
        return Gson().fromJson(json, type)
    }
}
