package com.redoven.graphlib.retrofit

import com.google.gson.annotations.SerializedName

class data {

    @SerializedName("prices")
    var prices:ArrayList<ArrayList<Double>>? = null

    @SerializedName("market_caps")
    var market_caps:ArrayList<ArrayList<Double>>? = null

    @SerializedName("total_volumes")
    var total_volumes:ArrayList<ArrayList<Double>>? = null

}