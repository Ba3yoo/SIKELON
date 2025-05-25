package com.rati.sikelon.navigate

import android.content.Context
import androidx.datastore.preferences.core.*
import androidx.datastore.preferences.preferencesDataStore
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.runBlocking

private val Context.dataStore by preferencesDataStore(name = "onboarding_prefs")
private val ONBOARDING_KEY = booleanPreferencesKey("onboarding_shown")

object OnboardingPreferences {
    fun isOnboardingShown(context: Context): Boolean = runBlocking {
        val prefs = context.dataStore.data.first()
        prefs[ONBOARDING_KEY] ?: false
    }

    suspend fun setOnboardingShown(context: Context) {
        context.dataStore.edit { prefs ->
            prefs[ONBOARDING_KEY] = true
        }
    }
}

object LoginPreferences {
    private const val PREFS_NAME = "login_prefs"
    private const val KEY_IS_LOGGED_IN = "is_logged_in"

    fun setLoggedIn(context: Context, loggedIn: Boolean) {
        val prefs = context.getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE)
        prefs.edit().putBoolean(KEY_IS_LOGGED_IN, loggedIn).apply()
    }

    fun isLoggedIn(context: Context): Boolean {
        val prefs = context.getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE)
        return prefs.getBoolean(KEY_IS_LOGGED_IN, false)
    }
}
