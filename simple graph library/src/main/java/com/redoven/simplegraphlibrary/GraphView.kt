package com.redoven.simplegraphlibrary

import android.content.Context
import android.graphics.*
import android.util.AttributeSet
import android.util.Log
import android.view.View

class GraphView @JvmOverloads constructor(
    context: Context, attrs: AttributeSet? = null, defStyleAttr: Int = 0
) : View(context, attrs, defStyleAttr) {

    //tag
    private val TAG = "GraphView"

    //scale
    private val scale = resources.displayMetrics.density

    /**
     * DRAWING VARIABLES
     */
    //props variable
    private val path = Path()
    private val paint = Paint()
    var color = Color.BLUE
    var strokewidth = 15f

    //temp
    private val w = resources.displayMetrics.widthPixels
    private val h = resources.displayMetrics.heightPixels


    /**
     * DATA VARIABLES
     */
    //divisions
    private var xDiv = 5f
    private var yDiv = 5f

    //graph dimensions
    private var gh = 250.0f*scale
    private var gw = 250.0f*scale

    //data
    private var data = ArrayList<ArrayList<Double>>()

    fun addValue(data:ArrayList<ArrayList<Double>>){
        this.data = data
        Log.d(TAG, "addValue: valuechanged , size : ${this.data.size}")
    }

    override fun onDraw(canvas: Canvas?) {
        super.onDraw(canvas)
//        Log.d(TAG, "onDraw: outside ${data.size}")
        if (data.size != 0) {
            init()

            canvas?.drawRect(Rect(0, 0, w, h), Paint().apply {
                color = Color.WHITE
            })
            canvas?.drawPath(path, paint)

//            Log.d(TAG, "onDraw: inside")
        }
        invalidate()
    }


    private fun init(){
        //data filtering
       data.apply {

           //scale of x
           sortBy { it[0] }

           xDiv = gw/Math.abs(get(0).get(0) - get(lastIndex).get(0)).toFloat()

           //scale of y
           sortBy { it[1] }
           yDiv = gh/Math.abs(get(0).get(1)-get(lastIndex).get(1)).toFloat()
       }

        //graphic props setup
        paint.apply {
            strokeWidth = this@GraphView.strokewidth
            color = this@GraphView.color
            style = Paint.Style.STROKE
        }

        //path generation method
        generateGraph()
    }

    private fun generateGraph(){
        var j = 0
        for (i in data){

            path.moveTo(i.get(0).toFloat()*xDiv, i.get(1).toFloat()*yDiv)

            //check if end reached
            if ((j+1) < data.size)
            path.lineTo(data.get(j+1).get(0).toFloat()*xDiv,
                data.get(j+1).get(1).toFloat()*yDiv)

            //update legend index
                  j++
        }
    }



}

