package com.example.ozinshe.data

import android.content.Context
import android.content.SharedPreferences

class SharedProvider(private val context: Context) {
    private val sharedToken = "SAVE_TOKEN"

    private val preference: SharedPreferences
        get() = context.getSharedPreferences(sharedToken, Context.MODE_PRIVATE)

    fun saveToken(token: String) {
        preference.edit().putString(sharedToken, token).apply()
    }

    fun getToken(): String {
        return preference.getString(sharedToken, "without_token").toString()
    }

    fun saveLanguage(language: String) {
        preference.edit().putString("language", language).apply()
    }

    fun getLanguage(): String {
        return preference.getString("language", "kk").toString()
    }

    fun clearShared() {
        preference.edit().clear().apply()
    }
}