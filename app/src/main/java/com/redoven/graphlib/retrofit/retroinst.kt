package com.redoven.graphlib.retrofit

import com.google.gson.Gson
import com.google.gson.GsonBuilder
import com.google.gson.internal.GsonBuildConfig
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object retroinst {

    val service by lazy {
        Retrofit.Builder().addConverterFactory(GsonConverterFactory.create(GsonBuilder().create()))
            .baseUrl("https://api.coingecko.com/api/v3/")
            .client(OkHttpClient())
            .build().create(calls::class.java)

    }

}