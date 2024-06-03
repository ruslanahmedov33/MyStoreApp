package com.example.mystore.Repository

import com.example.mystore.Api.RetrofitUtil
import com.example.mystore.data.CategoryData.CategoryDTO
import com.example.mystore.data.SettingsData.ProductDTO
import retrofit2.Call
import retrofit2.Response

class ApiRepoImpl : ApiRepository {
    private val service= RetrofitUtil.getService()


    override suspend fun getProductsByCategory(category: String): Response<CategoryDTO> {
        return service.getProductsByCategory(category)
    }

    override fun getProducts(): Call<ProductDTO> {
        return service.getAllProducts()
    }
}