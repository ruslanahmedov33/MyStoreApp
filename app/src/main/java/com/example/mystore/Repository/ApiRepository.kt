package com.example.mystore.Repository

import com.example.mystore.data.CategoryData.CategoryDTO
import com.example.mystore.data.SettingsData.ProductDTO
import retrofit2.Call
import retrofit2.Response

interface ApiRepository {
    fun getProducts():Call<ProductDTO>
    suspend fun getProductsByCategory(category: String): Response<CategoryDTO>

}