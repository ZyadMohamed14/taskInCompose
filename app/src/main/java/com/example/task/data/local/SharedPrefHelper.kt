package com.example.task.data.local

import android.content.Context
import android.content.SharedPreferences

class SharedPrefHelper(context: Context) {
    private val sharedPreferences: SharedPreferences = context.getSharedPreferences("ExamatePrefs", Context.MODE_PRIVATE)

    // Key for the overlay shown state
    private val overlayShownKey = "overlay_shown"

    // Function to check if the overlay has been shown
    fun isOverlayShown(): Boolean {
        return sharedPreferences.getBoolean(overlayShownKey, false)
    }

    // Function to set the overlay shown state
    fun setOverlayShown(shown: Boolean) {
        sharedPreferences.edit().putBoolean(overlayShownKey, shown).apply()
    }
}