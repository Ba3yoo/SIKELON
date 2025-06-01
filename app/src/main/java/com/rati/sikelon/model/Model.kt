package com.rati.sikelon.model

data class User(
    val user_id: Int,
    val email: String,
    val role: String,
    val username: String,
    val name: String,
    val password: String,
    val join_date: String
)

data class Store(
    val store_id: Int,
    val store_name: String,
    val address: String
)

data class Item(
    val item_id: Int,
    val item_name: String,
    val price: Int,
    val store_id: Int,
    val img_link: String
)

data class Cart(
    val cart_id: Int,
    val user_id: Int
)

data class CartDetail(
    val cartDetail_id: Int,
    val cart_id: Int,
    val store_id: Int,
    val item_id: Int,
    val quantity: Int,
    val store_name: String,
    val address: String,
    val item_name: String,
    val price: Int,
    val img_link: String
)

data class OrderItemModel(
    val id: String,
    val storeName: String,
    val storeIconResId: Int,
    val productInfo: String,
    val totalPrice: String
)


