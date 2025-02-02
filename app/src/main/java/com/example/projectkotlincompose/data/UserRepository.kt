package com.example.projectkotlincompose.data

import android.content.Context
import androidx.datastore.preferences.core.*
import androidx.datastore.preferences.preferencesDataStore
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.map

private val Context.dataStore by preferencesDataStore(name = "user_prefs")

class UserRepository(private val context: Context) {
    companion object {
        private val EMAIL_KEY = stringPreferencesKey("email")
        private val PASSWORD_KEY = stringPreferencesKey("password")
    }

    // Sauvegarde un utilisateur
    suspend fun saveUser(email: String, password: String) {
        context.dataStore.edit { preferences ->
            preferences[EMAIL_KEY] = email
            preferences[PASSWORD_KEY] = password
        }
    }

    // Vérifie si un compte existe et si le mot de passe est correct
    suspend fun isValidUser(email: String, password: String): Boolean {
        val prefs = context.dataStore.data.map { it }.first()
        val storedEmail = prefs[EMAIL_KEY]
        val storedPassword = prefs[PASSWORD_KEY]
        return storedEmail == email && storedPassword == password
    }

    // Vérifie si un compte existe déjà
    suspend fun isEmailTaken(email: String): Boolean {
        val prefs = context.dataStore.data.map { it }.first()
        return prefs[EMAIL_KEY] == email
    }
}
