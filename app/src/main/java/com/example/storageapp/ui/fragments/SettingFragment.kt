package com.example.storageapp.ui.fragments

import android.os.Bundle
import androidx.preference.PreferenceFragmentCompat
import com.example.storageapp.R

class SettingFragment:PreferenceFragmentCompat() {
    override fun onCreatePreferences(savedInstanceState: Bundle?, rootKey: String?) {
        return
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        addPreferencesFromResource(R.xml.preference)
    }
}