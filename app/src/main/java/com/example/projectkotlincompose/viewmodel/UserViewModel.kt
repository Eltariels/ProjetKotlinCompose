package com.example.projectkotlincompose.viewmodel

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.projectkotlincompose.data.UserRepository
import kotlinx.coroutines.launch
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

class UserViewModel(context: Context) : ViewModel() {
    private val userRepository = UserRepository(context)

    private val _currentUserEmail = MutableStateFlow<String?>(null)
    val currentUserEmail: StateFlow<String?> = _currentUserEmail

    fun registerUser(email: String, password: String, onSuccess: () -> Unit, onError: (String) -> Unit) {
        viewModelScope.launch {
            if (userRepository.isEmailTaken(email)) {
                onError("Cet email est déjà utilisé.")
            } else {
                userRepository.saveUser(email, password)
                _currentUserEmail.value = email // Définit l'utilisateur connecté après inscription
                onSuccess()
            }
        }
    }

    fun loginUser(email: String, password: String, onSuccess: () -> Unit, onError: (String) -> Unit) {
        viewModelScope.launch {
            if (userRepository.isValidUser(email, password)) {
                _currentUserEmail.value = email // Définit l'utilisateur connecté après connexion
                onSuccess()
            } else {
                onError("Identifiants incorrects.")
            }
        }
    }

    fun logout() {
        _currentUserEmail.value = null
    }
}
