package com.aplikasi.dicodingevents.data.di

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.preferencesDataStore
import com.dicoding.dicodingevents.data.remote.repository.EventRepository
import com.dicoding.dicodingevents.data.local.EventDatabase
import com.dicoding.dicodingevents.data.remote.retrofit.ApiConfig
import com.dicoding.dicodingevents.ui.settings.SettingsPreferences

private val Context.dataStore: DataStore<Preferences> by preferencesDataStore(name = "settings")
object Injection {

    fun provideRepository(context: Context): EventRepository {
        val apiService = ApiConfig.getApiService()
        val database = EventDatabase.getInstance(context)
        val dao = database.eventDao()
        return EventRepository.getInstance(apiService, dao)
    }
    fun provideSettingsPreferences(context: Context): SettingsPreferences {
        return SettingsPreferences.getInstance(context.dataStore)
    }
}
