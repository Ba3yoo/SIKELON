package com.rati.sikelon.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.rati.sikelon.data.Repo
import com.rati.sikelon.model.Model
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class ViewModel : ViewModel() {
    private val _users = MutableStateFlow<List<Model>>(emptyList())
    val users: StateFlow<List<Model>> = _users
    val repository = Repo()

    fun loadUsers() {
        viewModelScope.launch {
            //             (add
            //            _users.value = repository.fetchUsers()
        }
    }

//    suspend fun getUserById(id: Int): User {
//        return repository.fetchUserById(id)
//    }
}