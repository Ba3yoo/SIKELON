package com.rati.sikelon.service

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitInstance {
    private const val BASE_URL = "http://10.10.10.122:5000"

    private val retrofit: Retrofit by lazy {
        Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    val shopService: ShopService by lazy { retrofit.create(ShopService::class.java) }
    val loginService = retrofit.create(LoginService::class.java)
}