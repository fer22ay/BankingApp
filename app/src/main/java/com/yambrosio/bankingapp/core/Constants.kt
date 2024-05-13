package com.yambrosio.bankingapp.core

import androidx.datastore.preferences.core.stringSetPreferencesKey

object Constants {
    const val BASE_URL = "http://192.168.0.16:8080/"
    const val BASE_URL_API = "https://reqres.in/"
    const val AUTH_PREFERENCES = "AUTH_PREF"
    val AUTH_KEY = stringSetPreferencesKey("auth_key")
}