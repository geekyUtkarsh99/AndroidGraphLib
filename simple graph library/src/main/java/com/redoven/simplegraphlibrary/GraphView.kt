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

    //min max data
    private var minX = 0.0f
    private var maxX = 0.0f
    private var minY = 0.0f
    private var maxY = 0.0f

    //graph dimensions
    private var gh = 0.0f
    private var gw = 0.0f

    //data
    private var data = ArrayList<ArrayList<Double>>()

    fun addValue(data:ArrayList<ArrayList<Double>>){
        this.data = data

        Log.d(TAG, "addValue: valuechanged , size : ${this.data.size}")
    }

    override fun onSizeChanged(w: Int, h: Int, oldw: Int, oldh: Int) {
        super.onSizeChanged(w, h, oldw, oldh)

        gh = h.toFloat()
        gw = w.toFloat()
        Log.d(TAG, "onSizeChanged: size update w : $gw , h: $gh")

    }

    override fun onDraw(canvas: Canvas?) {
        super.onDraw(canvas)
//        Log.d(TAG, "onDraw: outside ${data.size}")
        if (data.size != 0 && gh != 0.0f) {
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

        if (gh != 0.0f) {

            //data filtering
            data.apply {
                //scale of y
                sortBy { it[1] }
//                Log.d(
//                    TAG,
//                    "addValue: scale1y : ${get(0).get(1)} ,scale2y: ${get(lastIndex).get(1)} "
//                )
                minY = get(0).get(1).toFloat()
                maxY = get(lastIndex).get(1).toFloat()
//                Log.d(TAG, "init: scale of  h: ${gh} y1: ${gh/(get(lastIndex).get(1) - get(0).get(1)).toFloat()}")
                yDiv = gh/(maxY - minY)

                //scale of x
                sortBy { it[0] }
//                Log.d(
//                    TAG,
//                    "addValue: scale1x : ${get(0).get(0)} ,scale2x: ${get(lastIndex).get(0)} "
//                )
                minX = get(0).get(0).toFloat()
                maxX = get(lastIndex).get(0).toFloat()
                xDiv = gw /(maxX - minX)

//                Log.d(TAG, "addValue: scale x : $xDiv , y : $yDiv")

            }
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

    /**
     * formula...f(x) = (b-a)(x-min)/(max-min)
     */
    private fun generateGraph(){
        var j = 0
        for (i in data){

            val tempx = (i.get(0).toFloat() - minX)*xDiv
            val tempy = gh -  (i.get(1).toFloat() - minY)*yDiv

//            Log.d(TAG, "generateGraph: observe x : $tempx , y : $tempy")
            path.moveTo(tempx, tempy)
//            path.moveTo(gh - i.get(1).toFloat()*yDiv, i.get(0).toFloat()*xDiv)

            //check if end reached
            if ((j+1) < data.size) {
                val tempxline = (data.get(j + 1).get(0).toFloat() - minX) * xDiv
                val tempyline =  gh - (data.get(j + 1).get(1).toFloat() - minY) * yDiv
//                Log.d(TAG, "generateGraph: observe xl : $tempxline , yl : $tempyline")

                path.lineTo(
                    tempxline,
                    tempyline
                )
//                path.lineTo(gh - data.get(j + 1).get(1).toFloat() * yDiv,
//                    data.get(j + 1).get(0).toFloat() * xDiv)
            }
            //update legend index
                  j++
        }
    }



}

