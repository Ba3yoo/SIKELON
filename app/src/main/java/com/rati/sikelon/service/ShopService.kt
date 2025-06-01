package com.rati.sikelon.service

import com.rati.sikelon.model.*
import retrofit2.Response
import retrofit2.http.*

interface ShopService {

    @GET("/func/user")
    suspend fun getUsers(): List<User>

    @GET("/func/user/{id}")
    suspend fun getUserById(@Path("id") id: Int): User

    @GET("/func/store")
    suspend fun getStores(): List<Store>

    @GET("/func/store/{id}")
    suspend fun getStoreById(@Path("id") id: Int): List<Store>

    @GET("/func/cart")
    suspend fun getCartsByUserId(@Query("userId") userId: Int): List<Cart>

    @GET("/func/cart/{id}")
    suspend fun getCartById(@Path("id") id: Int): Cart

    @GET("/func/item")
    suspend fun getItems(): List<Item>

    @GET("/func/item/{id}")
    suspend fun getItemById(@Path("id") id: Int): Item

    @GET("/func/cartdetail")
    suspend fun getCartDetails(): List<CartDetail>

    @GET("/func/cartdetail/{id}")
    suspend fun getCartDetailById(@Path("id") id: Int): List<CartDetail>

    @PUT("/func/cartdetail/{id}")
    suspend fun updateCartDetail(
        @Path("id") cartDetailId: Int,
        @Query("itemId") itemId: Int,
        @Query("quantity") quantity: Int
    ): CartDetail

    @DELETE("/func/cartdetail/{id}")
    suspend fun deleteCartDetail(
        @Path("id") cartDetailId: Int
    ): Response<Unit>
}
