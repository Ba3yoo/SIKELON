package com.rati.sikelon.data

import com.rati.sikelon.model.Cart
import com.rati.sikelon.model.CartDetail
import com.rati.sikelon.model.Item
import com.rati.sikelon.model.Store
import com.rati.sikelon.model.User
import com.rati.sikelon.service.RetrofitInstance
import com.rati.sikelon.service.ShopService

class UserRepository {

    private val apiService: ShopService = RetrofitInstance.ShopService

    suspend fun getUsers(): List<User> = apiService.getUsers()
    suspend fun getUserById(id: Int): User = apiService.getUserById(id)

    suspend fun getStores(): List<Store> = apiService.getStores()
    suspend fun getStoreById(id: Int): Store = apiService.getStoreById(id)

    suspend fun getCartsByUserId(userId: Int): List<Cart> = apiService.getCartsByUserId(userId)

    suspend fun getItems(): List<Item> = apiService.getItems()

    suspend fun getCartDetails(): List<CartDetail> = apiService.getCartDetails()

    suspend fun updateCartDetail(cartDetailId: Int, updatedDetail: CartDetail): CartDetail {
        return apiService.updateCartDetail(cartDetailId, updatedDetail)
    }

    suspend fun deleteCartDetail(cartDetailId: Int): Boolean {
        val response = apiService.deleteCartDetail(cartDetailId)
        return response.isSuccessful
    }
}
