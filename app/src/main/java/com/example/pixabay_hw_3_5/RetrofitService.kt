package com.example.pixabay_hw_3_5

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class RetrofitService {

    private val retrofit = Retrofit.Builder().baseUrl("https://pixabay.com/")
        .addConverterFactory(GsonConverterFactory.create()).build()

    val api = retrofit.create(PixaApi::class.java)

}