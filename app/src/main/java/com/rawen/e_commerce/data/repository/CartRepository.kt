package com.rawen.e_commerce.data.repository

import com.rawen.e_commerce.data.model.CartItem
import com.rawen.e_commerce.data.model.Product
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow

class CartRepository {
    private val _cartItems = MutableStateFlow<List<CartItem>>(emptyList())
    val cartItems: StateFlow<List<CartItem>> = _cartItems.asStateFlow()

    fun addToCart(product: Product) {
        val currentItems = _cartItems.value.toMutableList()
        val existingItem = currentItems.find { it.product.id == product.id }

        if (existingItem != null) {
            val index = currentItems.indexOf(existingItem)
            currentItems[index] = existingItem.copy(quantity = existingItem.quantity + 1)
        } else {
            currentItems.add(CartItem(product, 1))
        }

        _cartItems.value = currentItems
    }

    fun removeFromCart(productId: Int) {
        _cartItems.value = _cartItems.value.filter { it.product.id != productId }
    }

    fun updateQuantity(productId: Int, quantity: Int) {
        val currentItems = _cartItems.value.toMutableList()
        val existingItem = currentItems.find { it.product.id == productId }

        if (existingItem != null) {
            if (quantity <= 0) {
                removeFromCart(productId)
            } else {
                val index = currentItems.indexOf(existingItem)
                currentItems[index] = existingItem.copy(quantity = quantity)
                _cartItems.value = currentItems
            }
        }
    }

    fun clearCart() {
        _cartItems.value = emptyList()
    }

    fun getTotalPrice(): Double {
        return _cartItems.value.sumOf { it.totalPrice }
    }

    fun getItemCount(): Int {
        return _cartItems.value.sumOf { it.quantity }
    }
}
