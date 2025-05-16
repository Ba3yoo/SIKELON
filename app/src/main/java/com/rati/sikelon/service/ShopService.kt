package com.rati.sikelon.service

import com.rati.sikelon.model.*
import retrofit2.http.*

interface ShopService {

    @GET("users")
    suspend fun getUsers(): List<User>

    @GET("users/{id}")
    suspend fun getUserById(@Path("id") id: Int): User

    @GET("stores")
    suspend fun getStores(): List<Store>

    @GET("stores/{id}")
    suspend fun getStoreById(@Path("id") id: Int): Store

    @GET("carts")
    suspend fun getCartsByUserId(@Query("userId") userId: Int): List<Cart>

    @GET("items")
    suspend fun getItems(): List<Item>

    @GET("cartDetails")
    suspend fun getCartDetails(): List<CartDetail>

    @PUT("cartDetails/{id}")
    suspend fun updateCartDetail(
        @Path("id") cartDetailId: Int,
        @Body updatedDetail: CartDetail
    ): CartDetail
}
