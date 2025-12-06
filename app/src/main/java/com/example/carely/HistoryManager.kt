package com.example.carely


object HistoryManager {

    private val historyList = mutableListOf<History>()

    fun addHistory(history: History) {
        historyList.add(history)
    }

    fun getAllHistory(): List<History> = historyList
}
