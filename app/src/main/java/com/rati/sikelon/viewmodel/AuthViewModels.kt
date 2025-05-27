package com.rati.sikelon.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.rati.sikelon.data.AuthRepository
import com.rati.sikelon.data.AuthState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import com.rati.sikelon.data.Result
import com.rati.sikelon.model.requestResponse.*

class BuyerAuthViewModel(private val repository: AuthRepository) : ViewModel() {

    private val _authState = MutableStateFlow<AuthState>(AuthState.Idle)
    val authState: StateFlow<AuthState> = _authState

    fun registerBuyer(request: RegisterRequest) {
        _authState.value = AuthState.Loading
        viewModelScope.launch {
            when (val result = repository.registerBuyer(request)) {
                is Result.Success -> _authState.value = AuthState.Success(result.value.message)
                is Result.Failure -> _authState.value = AuthState.Error(result.exception.message ?: "Unknown Error")
                else -> {}
            }
        }
    }

    fun loginBuyer(request: LoginRequest) {
        _authState.value = AuthState.Loading
        viewModelScope.launch {
            when (val result = repository.loginBuyer(request)) {
                is Result.Success -> _authState.value = AuthState.Success(token = result.value.token)
                is Result.Failure -> _authState.value = AuthState.Error(result.exception.message ?: "Unknown Error")
                else -> {}
            }
        }
    }
}

class SellerAuthViewModel(private val repository: AuthRepository) : ViewModel() {

    private val _authState = MutableStateFlow<AuthState>(AuthState.Idle)
    val authState: StateFlow<AuthState> = _authState

    fun registerSeller(request: RegisterRequest) {
        _authState.value = AuthState.Loading
        viewModelScope.launch {
            when (val result = repository.registerSeller(request)) {
                is Result.Success -> _authState.value = AuthState.Success(result.value.message)
                is Result.Failure -> _authState.value = AuthState.Error(result.exception.message ?: "Unknown Error")
                else -> {}
            }
        }
    }

    fun loginSeller(request: LoginRequest) {
        _authState.value = AuthState.Loading
        viewModelScope.launch {
            when (val result = repository.loginSeller(request)) {
                is Result.Success -> _authState.value = AuthState.Success(token = result.value.token)
                is Result.Failure -> _authState.value = AuthState.Error(result.exception.message ?: "Unknown Error")
                else -> {}
            }
        }
    }
}

class DriverAuthViewModel(private val repository: AuthRepository) : ViewModel() {

    private val _authState = MutableStateFlow<AuthState>(AuthState.Idle)
    val authState: StateFlow<AuthState> = _authState

    fun registerDriver(request: RegisterRequest) {
        _authState.value = AuthState.Loading
        viewModelScope.launch {
            when (val result = repository.registerDriver(request)) {
                is Result.Success -> _authState.value = AuthState.Success(result.value.message)
                is Result.Failure -> _authState.value = AuthState.Error(result.exception.message ?: "Unknown Error")
                else -> {}
            }
        }
    }

    fun loginDriver(request: LoginRequest) {
        _authState.value = AuthState.Loading
        viewModelScope.launch {
            when (val result = repository.loginDriver(request)) {
                is Result.Success -> _authState.value = AuthState.Success(token = result.value.token)
                is Result.Failure -> _authState.value = AuthState.Error(result.exception.message ?: "Unknown Error")
                else -> {}
            }
        }
    }
}
