package com.rati.sikelon.viewmodel

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.rati.sikelon.data.AuthRepository
import com.rati.sikelon.data.AuthState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import com.rati.sikelon.data.Result
import com.rati.sikelon.data.SellerAuthState
import com.rati.sikelon.data.UserRepository
import com.rati.sikelon.model.User
import com.rati.sikelon.model.requestResponse.*

class BuyerAuthViewModel() : ViewModel() {
    private val repository = AuthRepository()
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

class SellerAuthViewModel() : ViewModel() {
    private val repository = AuthRepository()
    private val _authState = MutableStateFlow<AuthState>(AuthState.Idle)
    val authState: StateFlow<AuthState> = _authState
    private val _sellerAuthState = MutableStateFlow<SellerAuthState>(SellerAuthState.Idle)
    val sellerAuthState: StateFlow<SellerAuthState> = _sellerAuthState

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

//    private val _selectedSeller = MutableStateFlow<SellerLogin?>(null)
//    val selectedSeller: StateFlow<SellerLogin?> = _selectedSeller

    fun loginSeller(request: LoginRequest) {
        _sellerAuthState.value = SellerAuthState.Loading
        Log.d("stat", _sellerAuthState.value.toString())
        viewModelScope.launch {
            when (val result = repository.loginSeller(request)) {
                is Result.Success -> _sellerAuthState.value = SellerAuthState.Success(token = result.value.token, seller = result.value.seller)
                is Result.Failure -> _sellerAuthState.value = SellerAuthState.Error(result.exception.message ?: "Unknown Error")
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
