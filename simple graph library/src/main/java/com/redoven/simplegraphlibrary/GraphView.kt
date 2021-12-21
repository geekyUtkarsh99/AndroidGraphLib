package com.redoven.simplegraphlibrary

import android.content.Context
import android.graphics.*
import android.util.AttributeSet
import android.view.Surface
import android.view.SurfaceHolder
import android.view.SurfaceView
import android.view.View

class GraphView @JvmOverloads constructor(
    context: Context, attrs: AttributeSet? = null, defStyleAttr: Int = 0
) : View(context, attrs, defStyleAttr) {

    //scale
    private val scale = resources.displayMetrics.density

    /**
     * DRAWING VARIABLES
     */
    //props variable
    private val path = Path()
    private val paint = Paint()
    private var color = Color.BLUE
    private var strokewidth = 15f

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
    }

    override fun onDraw(canvas: Canvas?) {
        super.onDraw(canvas)
        if (data.size != 0) {
            init()

            canvas?.drawRect(Rect(0, 0, w, h), Paint().apply {
                color = Color.WHITE
            })
            canvas?.drawPath(path, paint)

        }
    }


    private fun init(){

        xDiv = gw/data.size
        yDiv = gh/data.size

        paint.apply {
            strokeWidth = this@GraphView.strokewidth
            color = this@GraphView.color
            style = Paint.Style.STROKE
        }

        path.moveTo(0.0f,gh)
        path.lineTo( 250.0f,gh + 250.0f)
        path.moveTo(250.0f,gh + 250.0f)
        path.lineTo(300.0f,gh + 270.0f)
    }

    private fun generateGraph(){
        var j = 0
        for (i in data){


            path.moveTo(j*xDiv,  differentiateYAxis(i.get(1).toFloat()))

            //check if end reached
            if ((j+1) < data.size)
            path.lineTo((j+1)*xDiv,  differentiateYAxis(data.get(j+1).get(1).toFloat()))

            //update legend index
                  j++
        }
    }

    private fun differentiateYAxis(value:Float):Float{



        return 0.0f
    }



}