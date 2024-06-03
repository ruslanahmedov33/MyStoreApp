package com.example.mystore.data.CategoryData


import com.example.mystore.data.SettingsData.Product
import com.google.gson.annotations.SerializedName

data class CategoryDTO(
    @SerializedName("limit")
    val limit: Int?,
    @SerializedName("products")
    val products: List<Product>?,
    @SerializedName("skip")
    val skip: Int?,
    @SerializedName("total")
    val total: Int?
)