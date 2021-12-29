package com.redoven.simplegraphlibrary

import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.graphics.Path
import android.icu.text.MessageFormat.format
import android.text.format.DateFormat.format
import java.lang.String.format
import java.sql.Timestamp
import java.text.DateFormat
import java.text.MessageFormat.format
import java.text.SimpleDateFormat
import java.util.*

class ValuePointer {


    //graphics
    private val paint = Paint()
    private val cPaint = Paint()

    private val tPaint = Paint()

    fun drawPoint(c: Canvas ,x:Float,y:Float , w:Float,h:Float,minX:Float,minY:Float,xDiv:Float,yDiv:Float,flag:Boolean){

        if (flag) {

            init()
            c.drawLine(x,h,x,75f,paint)
            c.drawCircle(x,h - y,4.5f*1.25f,cPaint)
            c.drawText(convertToDate(x,minX,xDiv),x,h - y,tPaint)

        }
    }

    private fun init(){
        paint.apply {
            color = Color.BLACK
            style = Paint.Style.STROKE
        }
        cPaint.apply {
            color = Color.BLUE
        }
        tPaint.apply {
            color = Color.BLACK
            textSize = 35f
        }
    }


    //convert timestamp to date
    fun convertToDate(value:Float,min:Float,div:Float) : String{
        val time = (value/div) + min
        val stamp = Timestamp(time.toLong())
//        return Date(stamp.time).toString()
        return SimpleDateFormat("dd/MM/yyyy").format(Date(stamp.time))
    }

    fun convertToYValue(value:Float,min:Float,div:Float):Float{

        return (value/div)+min
    }

}