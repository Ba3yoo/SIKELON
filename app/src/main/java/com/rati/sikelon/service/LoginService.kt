package com.rati.sikelon.service

import com.rati.sikelon.model.requestResponse.AuthResponse
import com.rati.sikelon.model.requestResponse.LoginRequest
import com.rati.sikelon.model.requestResponse.RegisterRequest
import retrofit2.http.Body
import retrofit2.http.POST

interface LoginService {

    @POST("/register/buyer")
    suspend fun registerBuyer(@Body request: RegisterRequest): AuthResponse

    @POST("/login/buyer")
    suspend fun loginBuyer(@Body request: LoginRequest): AuthResponse

    @POST("/register/seller")
    suspend fun registerSeller(@Body request: RegisterRequest): AuthResponse

    @POST("/login/seller")
    suspend fun loginSeller(@Body request: LoginRequest): AuthResponse

    @POST("/register/driver")
    suspend fun registerDriver(@Body request: RegisterRequest): AuthResponse

    @POST("/login/driver")
    suspend fun loginDriver(@Body request: LoginRequest): AuthResponse
}
