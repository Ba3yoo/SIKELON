package com.rati.sikelon.model.requestResponse


// Model data untuk request Register
data class RegisterRequest(
    val username: String,
    val name: String,
    val email: String,
    val password: String
)

// Model data untuk response register/login
data class AuthResponse(
    val message: String? = null,
    val token: String? = null
)

// Model data untuk request Login
data class LoginRequest(
    val email: String,
    val password: String
)
