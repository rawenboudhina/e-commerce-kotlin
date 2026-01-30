package com.rawen.e_commerce.data.repository

import com.rawen.e_commerce.data.api.RetrofitInstance
import com.rawen.e_commerce.data.model.Product
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class ProductRepository {
    private val api = RetrofitInstance.api

    suspend fun getAllProducts(): Result<List<Product>> = withContext(Dispatchers.IO) {
        try {
            val products = api.getAllProducts()
            Result.success(products)
        } catch (e: Exception) {
            Result.failure(e)
        }
    }

    suspend fun getProductById(id: Int): Result<Product> = withContext(Dispatchers.IO) {
        try {
            val product = api.getProductById(id)
            Result.success(product)
        } catch (e: Exception) {
            Result.failure(e)
        }
    }

    suspend fun getCategories(): Result<List<String>> = withContext(Dispatchers.IO) {
        try {
            val categories = api.getCategories()
            Result.success(categories)
        } catch (e: Exception) {
            Result.failure(e)
        }
    }

    suspend fun getProductsByCategory(category: String): Result<List<Product>> = withContext(Dispatchers.IO) {
        try {
            val products = api.getProductsByCategory(category)
            Result.success(products)
        } catch (e: Exception) {
            Result.failure(e)
        }
    }
}
