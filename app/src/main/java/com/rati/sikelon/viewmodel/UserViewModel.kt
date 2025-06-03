package com.rati.sikelon.viewmodel

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.rati.sikelon.R
import com.rati.sikelon.data.UserRepository
import com.rati.sikelon.model.Cart
import com.rati.sikelon.model.CartDetail
import com.rati.sikelon.model.Item
import com.rati.sikelon.model.Store
import com.rati.sikelon.model.StoreSearchResult
import com.rati.sikelon.model.User
import kotlinx.coroutines.async
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
        val storeList = repository.getStoreById(id)
        _selectedStore.value = storeList.firstOrNull()
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

        private val userRepository = UserRepository()

        private val _stores = MutableStateFlow<List<StoreSearchResult>>(emptyList())
        open val stores: StateFlow<List<StoreSearchResult>> = _stores

        private val _items = MutableStateFlow<List<Item>>(emptyList())
        val items: StateFlow<List<Item>> = _items

        private val _isLoading = MutableStateFlow(false)
        val isLoading: StateFlow<Boolean> = _isLoading

        private val _error = MutableStateFlow<String?>(null)
        val error: StateFlow<String?> = _error

        // Load stores function
        fun loadStores() = viewModelScope.launch {
            _isLoading.value = true
            _error.value = null
            try {
                val apiStores = userRepository.getStores()
                // Convert Store to StoreSearchResult
                val storeSearchResults = apiStores.map { store ->
                    StoreSearchResult(
                        storeId = store.store_id.toString(),
                        storeIconResId = R.drawable.toko_kurnia, // Default icon, you can map this based on store data
                        storeName = store.store_name,
                        storeLocationHint = store.store_location ?: "Lokasi tidak tersedia",
                        distance = calculateDistance(store), // You'll need to implement this
                        products = emptyList() // Will be populated when combining with items
                    )
                }
                _stores.value = storeSearchResults
                Log.d("SearchViewModel", "Loaded ${storeSearchResults.size} stores")
            } catch (e: Exception) {
                Log.e("SearchViewModel", "Error loading stores", e)
                _error.value = "Gagal memuat data toko: ${e.message}"
            } finally {
                _isLoading.value = false
            }
        }

        // Load items function
        fun loadItems() = viewModelScope.launch {
            _isLoading.value = true
            _error.value = null
            try {
                val itemsList = userRepository.getItems()
                _items.value = itemsList
                Log.d("SearchViewModel", "Loaded ${itemsList.size} items")
            } catch (e: Exception) {
                Log.e("SearchViewModel", "Error loading items", e)
                _error.value = "Gagal memuat data produk: ${e.message}"
            } finally {
                _isLoading.value = false
            }
        }

        // Load both stores and items together
        fun loadAllData() = viewModelScope.launch {
            _isLoading.value = true
            _error.value = null
            try {
                // Load both concurrently
                val storesDeferred = async { userRepository.getStores() }
                val itemsDeferred = async { userRepository.getItems() }

                val apiStores = storesDeferred.await()
                val itemsList = itemsDeferred.await()

                // Convert Store to StoreSearchResult
                val storeSearchResults = apiStores.map { store ->
                    StoreSearchResult(
                        storeId = store.store_id.toString(),
                        storeIconResId = getStoreIcon(store.store_name), // Map store name to icon
                        storeName = store.store_name,
                        storeLocationHint = store.store_location ?: "Lokasi tidak tersedia",
                        distance = calculateDistance(store),
                        products = emptyList() // Will be populated in UI
                    )
                }

                _stores.value = storeSearchResults
                _items.value = itemsList

                Log.d("SearchViewModel", "Loaded ${storeSearchResults.size} stores and ${itemsList.size} items")
            } catch (e: Exception) {
                Log.e("SearchViewModel", "Error loading data", e)
                _error.value = "Gagal memuat data: ${e.message}"
            } finally {
                _isLoading.value = false
            }
        }

        // Helper function to calculate distance (implement based on your needs)
        private fun calculateDistance(store: Store): String {
            // TODO: Implement actual distance calculation based on user location
            // For now, return a placeholder
            return "${(100..1000).random()}m"
        }

        // Helper function to map store name to icon resource
        private fun getStoreIcon(storeName: String): Int {
            return when {
                storeName.contains("Kurnia", ignoreCase = true) -> R.drawable.toko_kurnia
                storeName.contains("Makmur", ignoreCase = true) -> R.drawable.toko_kurnia // Add more icons as needed
                else -> R.drawable.toko_kurnia // Default icon
            }
        }

        // Clear error state
        fun clearError() {
            _error.value = null
        }
    }
    private val _cartDetails = MutableStateFlow<List<CartDetail>>(emptyList())
    val cartDetails: StateFlow<List<CartDetail>> = _cartDetails

    private val _selectedCartDetail = MutableStateFlow<List<CartDetail>>(emptyList())
    open val selectedCartDetail: StateFlow<List<CartDetail>> = _selectedCartDetail

    private val _updatedCartDetail = MutableStateFlow<CartDetail?>(null)
    val updatedCartDetail: StateFlow<CartDetail?> = _updatedCartDetail

    private val _cartDetailDeleted = MutableStateFlow<Boolean?>(null)
    val cartDetailDeleted: StateFlow<Boolean?> = _cartDetailDeleted

    fun loadCartDetails() = viewModelScope.launch {
        Log.d("cartdetail", _cartDetails.toString())
        _cartDetails.value = repository.getCartDetails()
    }

    fun loadCartDetailById(id: Int) = viewModelScope.launch {
        val result = repository.getCartDetailById(id)
        Log.d("cartdetail", result.toString())
        _selectedCartDetail.value = result
    }

    fun updateCartDetail(cartDetailId: Int, itemId: Int, quantity: Int) = viewModelScope.launch {
        _updatedCartDetail.value = repository.updateCartDetail(cartDetailId, itemId, quantity)
    }

    fun addToCart(storeId: Int, itemId: Int,) = viewModelScope.launch {
        repository.addToCart(storeId, itemId)
    }

    fun deleteCartDetail(cartDetailId: Int) = viewModelScope.launch {
        _cartDetailDeleted.value = repository.deleteCartDetail(cartDetailId)
        loadCartDetails()
    }
}
