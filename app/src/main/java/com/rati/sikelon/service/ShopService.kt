package com.rati.sikelon.service

import com.rati.sikelon.model.*
import retrofit2.http.*

interface ShopService {

    @GET("user")
    suspend fun getUsers(): List<User>

    @GET("user/{id}")
    suspend fun getUserById(@Path("id") id: Int): User

    @GET("store")
    suspend fun getStores(): List<Store>

    @GET("store/{id}")
    suspend fun getStoreById(@Path("id") id: Int): Store

    @GET("cart")
    suspend fun getCartsByUserId(@Query("userId") userId: Int): List<Cart>

    @GET("item")
    suspend fun getItems(): List<Item>

    @GET("cartdetail")
    suspend fun getCartDetails(): List<CartDetail>

    @PUT("cartdetail{id}")
    suspend fun updateCartDetail(
        @Path("id") cartDetailId: Int,
        @Body updatedDetail: CartDetail
    ): CartDetail
}
