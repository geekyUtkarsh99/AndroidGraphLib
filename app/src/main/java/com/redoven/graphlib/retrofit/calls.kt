package com.redoven.graphlib.retrofit

import retrofit2.Call
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.QueryMap


interface calls {

    @GET("coins/{id}/market_chart")
    fun getList(@Path("id")id:String , @QueryMap map:Map<String,String>): Call<data>





}