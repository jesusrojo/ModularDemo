package com.jesusrojo.common.di

import android.content.Context
import android.content.SharedPreferences
import androidx.preference.PreferenceManager
import com.jesusrojo.common.util.PrefsHelp
import dagger.hilt.android.qualifiers.ApplicationContext

class InjectionCommon {

    companion object{
        //PREFERENCES
        fun providePrefsHelp(prefs: SharedPreferences)
        : PrefsHelp =
            PrefsHelp(prefs)

        fun provideSharedPreferences(@ApplicationContext context: Context)
        : SharedPreferences =
            PreferenceManager.getDefaultSharedPreferences(context)
    }
}