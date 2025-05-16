package com.rati.sikelon.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.rati.sikelon.data.UserRepository
import com.rati.sikelon.model.*
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class UserViewModel(private val repository: UserRepository) : ViewModel() {

    private val _users = MutableStateFlow<List<User>>(emptyList())
    val users: StateFlow<List<User>> = _users

    private val _stores = MutableStateFlow<List<Store>>(emptyList())
    val stores: StateFlow<List<Store>> = _stores

    private val _selectedUser = MutableStateFlow<User?>(null)
    val selectedUser: StateFlow<User?> = _selectedUser

    private val _selectedStore = MutableStateFlow<Store?>(null)
    val selectedStore: StateFlow<Store?> = _selectedStore

    private val _userCarts = MutableStateFlow<List<Cart>>(emptyList())
    val userCarts: StateFlow<List<Cart>> = _userCarts

    private val _items = MutableStateFlow<List<Item>>(emptyList())
    val items: StateFlow<List<Item>> = _items

    private val _cartDetails = MutableStateFlow<List<CartDetail>>(emptyList())
    val cartDetails: StateFlow<List<CartDetail>> = _cartDetails

    private val _updatedCartDetail = MutableStateFlow<CartDetail?>(null)
    val updatedCartDetail: StateFlow<CartDetail?> = _updatedCartDetail

    fun loadUsers() = viewModelScope.launch {
        _users.value = repository.getUsers()
    }

    fun loadStores() = viewModelScope.launch {
        _stores.value = repository.getStores()
    }

    fun loadUserById(id: Int) = viewModelScope.launch {
        _selectedUser.value = repository.getUserById(id)
    }

    fun loadStoreById(id: Int) = viewModelScope.launch {
        _selectedStore.value = repository.getStoreById(id)
    }

    fun loadCartsByUserId(userId: Int) = viewModelScope.launch {
        _userCarts.value = repository.getCartsByUserId(userId)
    }

    fun loadItems() = viewModelScope.launch {
        _items.value = repository.getItems()
    }

    fun loadCartDetails() = viewModelScope.launch {
        _cartDetails.value = repository.getCartDetails()
    }

    fun updateCartDetail(cartDetailId: Int, updatedDetail: CartDetail) = viewModelScope.launch {
        _updatedCartDetail.value = repository.updateCartDetail(cartDetailId, updatedDetail)
    }
}
