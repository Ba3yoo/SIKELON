package com.rati.sikelon.viewmodel

import android.R
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.rati.sikelon.data.UserRepository
import com.rati.sikelon.model.*
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

open class UserViewModel() : ViewModel() {
    private val repository = UserRepository()
    private val _users = MutableStateFlow<List<User>>(emptyList())
    val users: StateFlow<List<User>> = _users

    private val _selectedUser = MutableStateFlow<User?>(null)
    val selectedUser: StateFlow<User?> = _selectedUser

    fun loadUsers() = viewModelScope.launch {
        _users.value = repository.getUsers()
    }

    fun loadUserById(id: Int) = viewModelScope.launch {
        _selectedUser.value = repository.getUserById(id)
    }

    private val _stores = MutableStateFlow<List<Store>>(emptyList())
    val stores: StateFlow<List<Store>> = _stores

    private val _selectedStore = MutableStateFlow<Store?>(null)
    val selectedStore: StateFlow<Store?> = _selectedStore



    fun loadStores() = viewModelScope.launch {
        _stores.value = repository.getStores()
    }

    fun loadStoreById(id: Int) = viewModelScope.launch {
        _selectedStore.value = repository.getStoreById(id)
    }

    private val _userCarts = MutableStateFlow<List<Cart>>(emptyList())
    val userCarts: StateFlow<List<Cart>> = _userCarts

    private val _selectedCart = MutableStateFlow<Cart?>(null)
    val selectedCart: StateFlow<Cart?> = _selectedCart

    fun loadCartsByUserId(userId: Int) = viewModelScope.launch {
        _userCarts.value = repository.getCartsByUserId(userId)
    }

    fun loadCartById(id: Int) = viewModelScope.launch {
        _selectedCart.value = repository.getCartById(id)
    }

    private val _items = MutableStateFlow<List<Item>>(emptyList())
    val items: StateFlow<List<Item>> = _items

    private val _selectedItem = MutableStateFlow<Item?>(null)
    val selectedItem: StateFlow<Item?> = _selectedItem

    fun loadItems() = viewModelScope.launch {
        _items.value = repository.getItems()
    }

    fun loadItemById(id: Int) = viewModelScope.launch {
        _selectedItem.value = repository.getItemById(id)
    }

    open class SearchViewModel : ViewModel() {

        private val _stores = MutableStateFlow<List<StoreSearchResult>>(emptyList())
        open val stores: StateFlow<List<StoreSearchResult>> = _stores

        private val _items = MutableStateFlow<List<Item>>(emptyList())
        val items: StateFlow<List<Item>> = _items

        // Empty load functions - implement as needed
        fun loadStores() = viewModelScope.launch {
            // Implement repository call here to load stores and update _stores.value
        }

        fun loadItems() = viewModelScope.launch {
            // Implement repository call here to load items and update _items.value
        }
    }

    private val _cartDetails = MutableStateFlow<List<CartDetail>>(emptyList())
    val cartDetails: StateFlow<List<CartDetail>> = _cartDetails

    private val _selectedCartDetail = MutableStateFlow<List<CartDetail>>(emptyList())
    val selectedCartDetail: StateFlow<List<CartDetail>> = _selectedCartDetail

    private val _updatedCartDetail = MutableStateFlow<CartDetail?>(null)
    val updatedCartDetail: StateFlow<CartDetail?> = _updatedCartDetail

    private val _cartDetailDeleted = MutableStateFlow<Boolean?>(null)
    val cartDetailDeleted: StateFlow<Boolean?> = _cartDetailDeleted

    fun loadCartDetails() = viewModelScope.launch {
        _cartDetails.value = repository.getCartDetails()
    }

    fun loadCartDetailById(id: Int) = viewModelScope.launch {
        _selectedCartDetail.value = repository.getCartDetailById(id)
    }

    fun updateCartDetail(cartDetailId: Int, itemId: Int, quantity: Int) = viewModelScope.launch {
        _updatedCartDetail.value = repository.updateCartDetail(cartDetailId, itemId, quantity)
    }

    fun deleteCartDetail(cartDetailId: Int) = viewModelScope.launch {
        _cartDetailDeleted.value = repository.deleteCartDetail(cartDetailId)
        loadCartDetails()
    }
}
