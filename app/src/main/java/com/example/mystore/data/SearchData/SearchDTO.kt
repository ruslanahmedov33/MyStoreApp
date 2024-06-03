package com.example.mystore.data.SearchData


import com.example.mystore.data.SettingsData.Product
import com.google.gson.annotations.SerializedName

data class SearchDTO(
    @SerializedName("limit")
    val limit: Int?,
    @SerializedName("products")
    val products: List<Product?>?,
    @SerializedName("skip")
    val skip: Int?,
    @SerializedName("total")
    val total: Int?
)