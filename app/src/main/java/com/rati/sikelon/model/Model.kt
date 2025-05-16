package com.rati.sikelon.model

data class User(
    val userId: Int,
    val username: String,
    val name: String,
    val joinDate: String
)

data class Store(
    val storeId: Int,
    val storeName: String,
    val address: String
)

data class Item(
    val itemId: Int,
    val itemName: String,
    val price: Int,
    val storeId: Int
)

data class Cart(
    val cartId: Int,
    val userId: Int
)

data class CartDetail(
    val cartDetailId: Int,
    val cartId: Int,
    val storeId: Int,
    val itemId: Int,
    val quantity: Int
)
