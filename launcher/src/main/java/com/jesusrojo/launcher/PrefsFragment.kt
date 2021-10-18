package com.jesusrojo.launcher

import android.os.Bundle

import androidx.preference.PreferenceFragmentCompat

//@AndroidEntryPoint
class PrefsFragment:  PreferenceFragmentCompat()  {

    override fun onCreatePreferences(savedInstanceState: Bundle?, rootKey: String?) {
        setPreferencesFromResource(R.xml.prefs_app, rootKey)
    }
}