package com.example.mystore.Api

import com.example.mystore.Service
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory


class RetrofitUtil {
    companion object{
        const val BASE_URL = "https://dummyjson.com/"

        fun getService(): Service {
            return Retrofit.Builder().baseUrl(BASE_URL).addConverterFactory(GsonConverterFactory.create()).build().create(
                Service::class.java)
        }
    }
}