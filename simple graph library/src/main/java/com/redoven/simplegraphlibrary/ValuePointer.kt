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
import java.util.*

class ValuePointer {


    //graphics
    private val path = Path()
    private val paint = Paint()

    private val cPaint = Paint()

    private var isDrawn = false

    fun drawPoint(c: Canvas ,x:Float,y:Float , w:Float,h:Float,flag:Boolean){

        if (flag) {
            if (isDrawn){
            init()
            isDrawn=!isDrawn
            }
            c.drawLine(x,h,x,0f,paint)
            c.drawCircle(x,h - y,4.5f*1.25f,cPaint)
//            c.drawPath(path, paint)
        }else {
            path.reset()
            isDrawn=!isDrawn
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
    }

    private fun drawLine(x:Float,y:Float, w:Float,h:Float){
        path.moveTo(x,h)
        path.lineTo(x,30.0f)
    }

    //convert timestamp to date
    fun convertToDate(value:Float,min:Float,div:Float) : String{
        val time = (value/div) + min
        val stamp = Timestamp(time.toLong())
        return Date(stamp.time).toString()
    }

}