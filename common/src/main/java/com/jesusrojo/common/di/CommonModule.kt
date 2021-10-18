package com.jesusrojo.common.di

import android.content.Context
import android.content.SharedPreferences
import androidx.preference.PreferenceManager
import com.jesusrojo.common.util.PrefsHelp
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
class CommonModule {

    //PREFERENCES
    @Provides
    @Singleton
    fun providePrefsHelp(prefs: SharedPreferences)
            : PrefsHelp =
        PrefsHelp(prefs)

    @Provides
    @Singleton
    fun provideSharedPreferences(@ApplicationContext context: Context)
            : SharedPreferences =
        PreferenceManager.getDefaultSharedPreferences(context)
}