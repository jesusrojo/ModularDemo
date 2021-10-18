package com.jesusrojo.common.util

import android.content.SharedPreferences
import androidx.core.content.edit
import javax.inject.Inject

class PrefsHelp @Inject constructor(val prefs: SharedPreferences) {

    companion object {
        const val SHOULD_UPDATE_KEY = "SHOULD_UPDATE_KEY"
    }

    suspend fun setShouldUpdate(shouldUpdate: Boolean) {
        prefs.edit {
            putBoolean(SHOULD_UPDATE_KEY, shouldUpdate)
        }
    }

    suspend fun getShouldUpdate(): Boolean =
        prefs.getBoolean(SHOULD_UPDATE_KEY, true)

}