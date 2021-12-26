package com.redoven.simplegraphlibrary

import android.graphics.Canvas
import android.graphics.Paint
import android.graphics.Path
import android.icu.text.MessageFormat.format
import android.text.format.DateFormat.format
import java.lang.String.format
import java.text.DateFormat
import java.text.MessageFormat.format
import java.util.*

class ValuePointer {


    //graphics
    private val path = Path()
    private val paint = Paint()

    fun drawPoint(c: Canvas ,x:Float,y:Float , w:Float,h:Float){



    }

    fun convertToDate(value:Float,min:Float,div:Float) : String{
        val time = (value/div) + min
        val Cal = Calendar.getInstance(Locale.ENGLISH)
        Cal.timeInMillis = (time*1000L).toLong()

        return DateFormat.getInstance().format(Cal.time)
    }

}