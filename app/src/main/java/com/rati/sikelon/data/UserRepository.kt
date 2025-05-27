package com.rati.sikelon.data

import com.rati.sikelon.model.Cart
import com.rati.sikelon.model.CartDetail
import com.rati.sikelon.model.Item
import com.rati.sikelon.model.Store
import com.rati.sikelon.model.User
import com.rati.sikelon.model.requestResponse.AuthResponse
import com.rati.sikelon.model.requestResponse.LoginRequest
import com.rati.sikelon.model.requestResponse.RegisterRequest
import com.rati.sikelon.service.LoginService
import com.rati.sikelon.service.RetrofitInstance
import com.rati.sikelon.service.ShopService

class UserRepository {

    private val apiService: ShopService = RetrofitInstance.shopService

    suspend fun getUsers(): List<User> = apiService.getUsers()
    suspend fun getUserById(id: Int): User = apiService.getUserById(id)

    suspend fun getStores(): List<Store> = apiService.getStores()
    suspend fun getStoreById(id: Int): Store = apiService.getStoreById(id)

    suspend fun getCartsByUserId(userId: Int): List<Cart> = apiService.getCartsByUserId(userId)
    suspend fun getCartById(id: Int): Cart = apiService.getCartById(id)

    suspend fun getItems(): List<Item> = apiService.getItems()
    suspend fun getItemById(id: Int): Item = apiService.getItemById(id)

    suspend fun getCartDetails(): List<CartDetail> = apiService.getCartDetails()
    suspend fun getCartDetailById(id: Int): List<CartDetail> = apiService.getCartDetailById(id)

    suspend fun updateCartDetail(cartDetailId: Int, itemId: Int, quantity: Int): CartDetail =
        apiService.updateCartDetail(cartDetailId, itemId, quantity)

    suspend fun deleteCartDetail(cartDetailId: Int): Boolean =
        apiService.deleteCartDetail(cartDetailId).isSuccessful
}

class AuthRepository() {
    private val loginService = RetrofitInstance.loginService
    suspend fun registerBuyer(request: RegisterRequest): Result<AuthResponse> {
        return safeApiCall { loginService.registerBuyer(request) }
    }

    suspend fun loginBuyer(request: LoginRequest): Result<AuthResponse> {
        return safeApiCall { loginService.loginBuyer(request) }
    }

    // Sama untuk seller dan driver...
    suspend fun registerSeller(request: RegisterRequest): Result<AuthResponse> {
        return safeApiCall { loginService.registerSeller(request) }
    }

    suspend fun loginSeller(request: LoginRequest): Result<AuthResponse> {
        return safeApiCall { loginService.loginSeller(request) }
    }

    suspend fun registerDriver(request: RegisterRequest): Result<AuthResponse> {
        return safeApiCall { loginService.registerDriver(request) }
    }

    suspend fun loginDriver(request: LoginRequest): Result<AuthResponse> {
        return safeApiCall { loginService.loginDriver(request) }
    }

    // Implementasi safeApiCall dengan Result custom
    private inline fun <T> safeApiCall(apiCall: () -> T): Result<T> {
        return try {
            val response = apiCall()
            Result.Success(response)
        } catch (e: Exception) {
            Result.Failure(e)
        }
    }
}

sealed class Result<out T> {
    data class Success<out T>(val value: T) : Result<T>()
    data class Failure(val exception: Throwable) : Result<Nothing>()
}

sealed class AuthState {
    object Idle : AuthState()
    object Loading : AuthState()
    data class Success(val message: String? = null, val token: String? = null) : AuthState()
    data class Error(val error: String) : AuthState()
}
