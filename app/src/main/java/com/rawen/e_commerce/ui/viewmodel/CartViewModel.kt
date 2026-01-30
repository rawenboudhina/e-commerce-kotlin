package com.rawen.e_commerce.ui.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.rawen.e_commerce.data.model.CartItem
import com.rawen.e_commerce.data.model.Product
import com.rawen.e_commerce.data.repository.CartRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class CartViewModel : ViewModel() {
    private val repository = CartRepository()

    val cartItems: StateFlow<List<CartItem>> = repository.cartItems

    private val _totalPrice = MutableStateFlow(0.0)
    val totalPrice: StateFlow<Double> = _totalPrice.asStateFlow()

    private val _itemCount = MutableStateFlow(0)
    val itemCount: StateFlow<Int> = _itemCount.asStateFlow()

    private val _orderPlaced = MutableStateFlow(false)
    val orderPlaced: StateFlow<Boolean> = _orderPlaced.asStateFlow()

    init {
        viewModelScope.launch {
            repository.cartItems.collect { items ->
                _totalPrice.value = repository.getTotalPrice()
                _itemCount.value = repository.getItemCount()
            }
        }
    }

    fun addToCart(product: Product) {
        repository.addToCart(product)
    }

    fun removeFromCart(productId: Int) {
        repository.removeFromCart(productId)
    }

    fun updateQuantity(productId: Int, quantity: Int) {
        repository.updateQuantity(productId, quantity)
    }

    fun placeOrder() {
        viewModelScope.launch {
            // In a real app, you would send the order to a backend
            repository.clearCart()
            _orderPlaced.value = true
        }
    }

    fun resetOrderPlaced() {
        _orderPlaced.value = false
    }
}
