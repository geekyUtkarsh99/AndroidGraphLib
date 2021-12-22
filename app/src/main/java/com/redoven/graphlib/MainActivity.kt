package com.redoven.graphlib

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.redoven.graphlib.databinding.ActivityMainBinding
import com.redoven.simplegraphlibrary.GraphView

//test activity
class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        binding = ActivityMainBinding.inflate(layoutInflater)



            val data = ArrayList<ArrayList<Double>>()

            data.add(ArrayList<Double>().apply {
                add(10.0)
                add(12.0)
            })
            data.add(ArrayList<Double>().apply {
                add(15.0)
                add(256.0)
            })
            data.add(ArrayList<Double>().apply {
                add(48.0)
                add(14.0)
            })
            data.add(ArrayList<Double>().apply {
                add(365.0)
                add(145.0)
            })
            data.add(ArrayList<Double>().apply {
                add(12.0)
                add(32.0)
            })
            data.add(ArrayList<Double>().apply {
                add(14.25)
                add(12.145)
            })
            data.add(ArrayList<Double>().apply {
                add(125.45)
                add(123.12)
            })
            data.add(ArrayList<Double>().apply {
                add(19.0)
                add(15.0)
            })
        data.add(ArrayList<Double>().apply {
            add(20.0)
            add(12.0)
        })
        data.add(ArrayList<Double>().apply {
            add(21.0)
            add(256.0)
        })
        data.add(ArrayList<Double>().apply {
            add(22.0)
            add(14.0)
        })
        data.add(ArrayList<Double>().apply {
            add(23.0)
            add(145.0)
        })
        data.add(ArrayList<Double>().apply {
            add(12.0)
            add(32.0)
        })
        data.add(ArrayList<Double>().apply {
            add(24.25)
            add(12.145)
        })
        data.add(ArrayList<Double>().apply {
            add(25.45)
            add(123.12)
        })
        data.add(ArrayList<Double>().apply {
            add(26.0)
            add(15.0)
        })


        findViewById<GraphView>(R.id.graph).apply{
            strokewidth = 3f
            addValue(data)
        }







    }
}