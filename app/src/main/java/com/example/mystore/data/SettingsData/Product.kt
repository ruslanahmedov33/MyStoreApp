package com.example.mystore.data.SettingsData


import com.google.gson.annotations.SerializedName

data class Product(
    @SerializedName("brand")
    val brand: String?,
    @SerializedName("category")
    val category: String?,
    @SerializedName("description")
    val description: String?,
    @SerializedName("discountPercentage")
    val discountPercentage: Double?,
    @SerializedName("id")
    val id: Int?,
    @SerializedName("images")
    val images: List<String?>?,
    @SerializedName("price")
    val price: Double?,
    @SerializedName("rating")
    val rating: Double?,
    @SerializedName("stock")
    val stock: Int?,
    @SerializedName("thumbnail")
    val thumbnail: String?,
    @SerializedName("title")
    val title: String?
)