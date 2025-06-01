package com.rati.sikelon.service

import com.rati.sikelon.model.requestResponse.AuthResponse
import com.rati.sikelon.model.requestResponse.LoginRequest
import com.rati.sikelon.model.requestResponse.RegisterRequest
import com.rati.sikelon.model.requestResponse.SellerLogin
import retrofit2.http.Body
import retrofit2.http.POST

interface LoginService {

    @POST("/auth/register/buyer")
    suspend fun registerBuyer(@Body request: RegisterRequest): AuthResponse

    @POST("/auth/login/buyer")
    suspend fun loginBuyer(@Body request: LoginRequest): AuthResponse

    @POST("/auth/register/seller")
    suspend fun registerSeller(@Body request: RegisterRequest): SellerLogin

    @POST("/auth/login/seller")
    suspend fun loginSeller(@Body request: LoginRequest): SellerLogin

    @POST("/auth/register/driver")
    suspend fun registerDriver(@Body request: RegisterRequest): AuthResponse

    @POST("/auth/login/driver")
    suspend fun loginDriver(@Body request: LoginRequest): AuthResponse
}
