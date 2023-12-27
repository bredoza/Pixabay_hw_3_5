package com.example.pixabay_hw_3_5

import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface PixaApi {

    @GET("api/")
    fun getImages(
        @Query("q") keyWord: String,
        @Query("key") key: String = "41490581-387d50d01a05347d97e7e8e1e",
        @Query("page") page: Int,
        @Query("per_page") perPage: Int = 9
    ): Call<PixaModel>
}