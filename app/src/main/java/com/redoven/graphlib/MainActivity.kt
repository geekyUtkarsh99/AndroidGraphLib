package com.redoven.graphlib

import android.annotation.SuppressLint
import android.content.pm.ActivityInfo
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.redoven.graphlib.databinding.ActivityMainBinding
import com.redoven.simplegraphlibrary.GraphView

//test activity
class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

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


        findViewById<GraphView>(R.id.graph).apply{
            strokewidth = 4.5f
            addValue(data)
        }







    }
}