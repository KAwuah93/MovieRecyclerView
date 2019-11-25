package com.example.movierecyclerview.model.datasource.remote.retrofit

import com.example.movierecyclerview.model.datasource.remote.retrofit.CONSTANTS.Companion.BASE_URL
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory

class RetrofitHelper {
    //Within the class we want to create a function that 'returns' an instance
    //of retrofit to the specifications that we've created
    fun getRetrofitInstance() : Retrofit = Retrofit.Builder()
        .baseUrl(BASE_URL)
        .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
        .addConverterFactory(GsonConverterFactory.create())
        .build()
}