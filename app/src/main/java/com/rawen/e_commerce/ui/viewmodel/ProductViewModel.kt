package com.rawen.e_commerce.ui.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.rawen.e_commerce.data.model.Product
import com.rawen.e_commerce.data.repository.ProductRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

sealed class ProductUiState {
    object Loading : ProductUiState()
    data class Success(val products: List<Product>) : ProductUiState()
    data class Error(val message: String) : ProductUiState()
}

class ProductViewModel : ViewModel() {
    private val repository = ProductRepository()

    private val _uiState = MutableStateFlow<ProductUiState>(ProductUiState.Loading)
    val uiState: StateFlow<ProductUiState> = _uiState.asStateFlow()

    private val _selectedCategory = MutableStateFlow<String?>(null)
    val selectedCategory: StateFlow<String?> = _selectedCategory.asStateFlow()

    init {
        loadProducts()
    }

    fun loadProducts() {
        viewModelScope.launch {
            _uiState.value = ProductUiState.Loading
            val result = repository.getAllProducts()
            _uiState.value = result.fold(
                onSuccess = { ProductUiState.Success(it) },
                onFailure = { ProductUiState.Error(it.message ?: "Unknown error occurred") }
            )
        }
    }

    fun loadProductsByCategory(category: String) {
        viewModelScope.launch {
            _uiState.value = ProductUiState.Loading
            _selectedCategory.value = category
            val result = repository.getProductsByCategory(category)
            _uiState.value = result.fold(
                onSuccess = { ProductUiState.Success(it) },
                onFailure = { ProductUiState.Error(it.message ?: "Unknown error occurred") }
            )
        }
    }

    fun clearCategoryFilter() {
        _selectedCategory.value = null
        loadProducts()
    }
}
