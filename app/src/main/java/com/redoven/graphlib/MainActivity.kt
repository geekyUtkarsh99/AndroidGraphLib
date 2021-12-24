package com.redoven.graphlib

import android.annotation.SuppressLint
import android.content.pm.ActivityInfo
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.lifecycle.MutableLiveData
import com.redoven.graphlib.databinding.ActivityMainBinding
import com.redoven.graphlib.retrofit.data
import com.redoven.graphlib.retrofit.retroinst
import com.redoven.simplegraphlibrary.GraphView
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit

//test activity
class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    private val TAG = "MainActivity"

    @SuppressLint("SourceLockedOrientationActivity")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        requestedOrientation = ActivityInfo.SCREEN_ORIENTATION_PORTRAIT

        binding = ActivityMainBinding.inflate(layoutInflater)

            val data = ArrayList<ArrayList<Double>>()

            data.add(ArrayList<Double>().apply {
                add(11.0)
                add(14.0)
            })
            data.add(ArrayList<Double>().apply {
                add(20.0)
                add(265.0)
            })
            data.add(ArrayList<Double>().apply {
                add(30.0)
                add(45.0)
            })
            data.add(ArrayList<Double>().apply {
                add(40.0)
                add(325.0)
            })
            data.add(ArrayList<Double>().apply {
                add(50.0)
                add(11.0)
            })
            data.add(ArrayList<Double>().apply {
                add(60.25)
                add(154.145)
            })
            data.add(ArrayList<Double>().apply {
                add(70.45)
                add(24.12)
            })
            data.add(ArrayList<Double>().apply {
                add(80.0)
                add(132.0)
            })
        data.add(ArrayList<Double>().apply {
            add(90.0)
            add(133.0)
        })
        data.add(ArrayList<Double>().apply {
            add(100.0)
            add(134.0)
        })
        data.add(ArrayList<Double>().apply {
            add(110.0)
            add(135.0)
        })
        data.add(ArrayList<Double>().apply {
            add(120.0)
            add(136.0)
        })
        data.add(ArrayList<Double>().apply {
            add(130.0)
            add(137.0)
        })
        data.add(ArrayList<Double>().apply {
            add(140.0)
            add(138.145)
        })
        data.add(ArrayList<Double>().apply {
            add(150.0)
            add(139.12)
        })
        data.add(ArrayList<Double>().apply {
            add(160.0)
            add(140.0)
        })

//        binding.apply {
//
//            graph.apply {
//                strokewidth = 4.5f
//                addValue(data)
//            }
//        }

        var dataset = MutableLiveData<ArrayList<ArrayList<Double>>>()

        retroinst.service.getList("bitcoin" , mapOf(

            "vs_currency " to "inr",
            "days " to "1"

        ) ).enqueue(object:Callback<data>{
            override fun onResponse(call: Call<data>, response: Response<data>) {
                val res = response.body()

                Log.d(TAG, "onResponse: ${res?.prices.toString()}")
                dataset.value= res?.prices

            }

            override fun onFailure(call: Call<data>, t: Throwable) {
                Log.e(TAG, "onFailure:  ${t.message}", t)
            }

        })

        dataset.observe(this, {
            findViewById<GraphView>(R.id.graph).apply{
                if (it != null) {

                    strokewidth = 4.5f
                    addValue(it)
                }
            }
        })








    }
}