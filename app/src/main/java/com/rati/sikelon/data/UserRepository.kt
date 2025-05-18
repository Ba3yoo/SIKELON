package com.rati.sikelon.data

import com.rati.sikelon.model.*
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
