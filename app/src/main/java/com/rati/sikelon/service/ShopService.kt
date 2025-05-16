package com.rati.sikelon.service

import com.rati.sikelon.model.*
import retrofit2.Response
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

    @GET("cart/{id}")
    suspend fun getCartById(@Path("id") id: Int): Cart

    @GET("item")
    suspend fun getItems(): List<Item>

    @GET("item/{id}")
    suspend fun getItemById(@Path("id") id: Int): Item

    @GET("cartdetail")
    suspend fun getCartDetails(): List<CartDetail>

    @GET("cartdetail/{id}")
    suspend fun getCartDetailById(@Path("id") id: Int): CartDetail

    @PUT("cartdetail/{id}")
    suspend fun updateCartDetail(
        @Path("id") cartDetailId: Int,
        @Query("itemId") itemId: Int,
        @Query("quantity") quantity: Int
    ): CartDetail

    @DELETE("cartdetail/{id}")
    suspend fun deleteCartDetail(
        @Path("id") cartDetailId: Int
    ): Response<Unit>
}
