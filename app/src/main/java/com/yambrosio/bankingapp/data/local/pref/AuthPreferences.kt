package com.yambrosio.bankingapp.data.local.pref

import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import com.yambrosio.bankingapp.core.Constants.AUTH_KEY
import javax.inject.Inject

class AuthPreferences @Inject constructor(private val dataStore: DataStore<Preferences>) {
    suspend fun saveAuthToken(loginToken: String) {
        dataStore.edit { pref ->
            pref[AUTH_KEY] = setOf(loginToken)
        }
    }
}