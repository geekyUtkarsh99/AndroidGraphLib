package com.redoven.simplegraphlibrary

import android.graphics.*
import android.icu.text.MessageFormat.format
import android.text.format.DateFormat.format
import android.util.Log
import androidx.annotation.ColorRes
import java.lang.String.format
import java.sql.Timestamp
import java.text.DateFormat
import java.text.MessageFormat.format
import java.text.NumberFormat
import java.text.SimpleDateFormat
import java.util.*

class ValuePointer {

    //graphics
    private val paint = Paint()
    private val cPaint = Paint()

    private val tPaint = Paint()
    private val rPAint = Paint()

    fun drawPoint(c: Canvas ,x:Float,y:Float , w:Float,h:Float,minX:Float,minY:Float,xDiv:Float,yDiv:Float,flag:Boolean){

        if (flag) {

            init()
            c.drawLine(x,h,x,35f,paint)
            c.drawCircle(x,h - y,4.5f*1.25f,cPaint) //pointer

            //display x and y points
            val date = convertToDate(x, minX, xDiv)
            val yVal =  NumberFormat.getInstance(Locale("en", "IN"))
                .format(convertToYValue(y, minY, yDiv))
            val tempPaint = Paint()
            tempPaint.textSize = 35f
            val sizeA = tempPaint.measureText(date,0,date.length)
            val sizeB = tempPaint.measureText(yVal,0,yVal.length)
            if (x < w/2) {
                c.drawRoundRect(x,35f,x+sizeA+15f,45f+40f*2,15f,15f,rPAint) //draw balloon-left
//                c.drawRoundRect(x,35f,x+sizeA+15f,45f+40f*2,15f,15f,Paint().apply { style =
//                    Paint.Style.STROKE
//                color = Color.BLACK
//                    strokeWidth = 3f
//                }) //draw balloon-left border
                c.drawText(date, x+10f, 75f, tPaint)
                c.drawText(yVal, x+10f, 75f + 40f, tPaint)
            }else if (x >=w/2){
                c.drawRoundRect(x-sizeA-15f,35f,x,45f+40f*2,15f,15f,rPAint)//draw balloon-right
//                c.drawRoundRect(x,35f,x+sizeA+15f,45f+40f*2,15f,15f,Paint().apply { style =
//                    Paint.Style.STROKE
//                    color = Color.LTGRAY
//                    strokeWidth = 3f
//                }) //draw balloon-right border
                c.drawText(date, x - sizeA-10f, 75f, tPaint)
                c.drawText(yVal, x - sizeB-10f, 75f + 40f, tPaint)
            }
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
        rPAint.apply {
            color = Color.parseColor("#4000FFFF")
        }
    }

    //convert timestamp to date
    fun convertToDate(value:Float,min:Float,div:Float) : String{
        val time = (value/div) + min
        val stamp = Timestamp(time.toLong())
//        return Date(stamp.time).toString()
        return SimpleDateFormat("HH:mm:ss | dd/MM/yyyy").format(Date(stamp.time))
    }

    //convert to the y original value
    fun convertToYValue(value:Float,min:Float,div:Float):Float{

        return (value/div)+min
    }

}