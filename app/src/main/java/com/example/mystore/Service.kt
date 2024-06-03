package com.example.mystore

import com.example.mystore.data.CategoryData.CategoryDTO
import com.example.mystore.data.SettingsData.ProductDTO
import com.example.mystore.data.SingleProduct.ProductDetailsDTO
import retrofit2.Call
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query


interface Service {
        @GET("products")
        fun getAllProducts():Call<ProductDTO>

        @GET("products/{id}")
        fun getProductDetails(@Path("id")id:Int):Call<ProductDetailsDTO>

        @GET("products/search")
        fun searchProducts(@Query("q") search:String): Call<ProductDTO>

        @GET("products/category/{category}")
        suspend fun getProductsByCategory(@Path("category") category: String): Response<CategoryDTO>
}
