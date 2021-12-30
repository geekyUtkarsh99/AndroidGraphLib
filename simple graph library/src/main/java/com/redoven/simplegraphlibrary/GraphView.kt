package com.redoven.simplegraphlibrary

import android.annotation.SuppressLint
import android.content.Context
import android.graphics.*
import android.util.AttributeSet
import android.util.Log
import android.view.DragEvent
import android.view.MotionEvent
import android.view.View
import java.sql.Timestamp
import java.text.DateFormat
import java.text.SimpleDateFormat
import java.util.*
import kotlin.collections.ArrayList
import java.time.*

class GraphView @JvmOverloads constructor(
    context: Context, attrs: AttributeSet? = null, defStyleAttr: Int = 0
) : View(context, attrs, defStyleAttr){

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
    var color = Color.parseColor("#8C9EFF")
    var strokewidth = 15f
    private val pointer = ValuePointer()

    //area vars
    private val arPath = Path()
    private val arPaint = Paint()
    var arColor = Color.parseColor("#40D1EEFC")

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

    //scale differentiator
    private var diff = 130f

    //bools
    private var viewPoint = false
    private var drawComplete = false
    
    //graph coordinates
    private var xCord = 0.0f
    private var yCord = 0.0f

    //data
    private var data = ArrayList<ArrayList<Double>>()

    fun addValue(data:ArrayList<ArrayList<Double>>){
        this.data = data

        Log.d(TAG, "addValue: valuechanged , size : ${this.data.size}")

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
                yDiv = (gh-diff)/(maxY - minY)

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

        requestLayout()
    }

    override fun onSizeChanged(w: Int, h: Int, oldw: Int, oldh: Int) {
        super.onSizeChanged(w, h, oldw, oldh)

        gh = h.toFloat()
        gw = w.toFloat()
        Log.d(TAG, "onSizeChanged: size update w : $gw , h: $gh")

    }

    override fun onDraw(canvas: Canvas?) {
        super.onDraw(canvas)
        if (data.size != 0 && gh != 0.0f) {
            if (!drawComplete) {
                init()
            }
                canvas?.drawRect(Rect(0, 0, w, h), Paint().apply {
                    color = Color.WHITE
                })
                canvas?.drawPath(path, paint)
                canvas?.drawPath(arPath, arPaint)
                drawComplete = true

            pointer.drawPoint(canvas!!,xCord,yCord,gw,gh,minX,minY,xDiv,yDiv,viewPoint)
            invalidate()
        }else {
            invalidate()
            requestLayout()
        }
    }


    private fun init(){

        //graphic props setup
        paint.apply {
            strokeWidth = this@GraphView.strokewidth
            color = this@GraphView.color
            style = Paint.Style.STROKE
        }

        arPaint.apply {
            style = Paint.Style.FILL
            color = this@GraphView.arColor
        }

        //path generation method
        generateGraph()
    }

    /**
     * formula...f(x) = (b-a)(x-min)/(max-min)
     * scale...s = (b-a)/(max-min)
     */
    private fun generateGraph(){
        var j = 0
        for (i in data){

            //stroke
            val tempx = (i.get(0).toFloat() - minX)*xDiv
            val tempy = (gh) -  (i.get(1).toFloat() - minY)*yDiv
            path.moveTo(tempx, tempy)

//            Log.d(TAG, "generateGraph: xpos:${tempx}")

            //fill
            arPath.apply {
                moveTo(tempx, gh)
                lineTo(tempx, tempy)
            }

            //check if end reached
            if ((j+1) < data.size) {
                val tempxline = (data.get(j + 1).get(0).toFloat() - minX) * xDiv
                val tempyline =  (gh) - (data.get(j + 1).get(1).toFloat() - minY) * yDiv

                //stroke
                path.lineTo(
                    tempxline,
                    tempyline
                )

                //fill
                arPath.apply {
                    lineTo(tempxline,tempyline)
                    lineTo(tempxline,gh)
                }

            }

            //update legend index
                  j++
        }
    }


    private fun findMatch(x:Float):ArrayList<Double>{
        val temp = ArrayList<Double>()

        for (i in data){

            val coord = (i.get(0).toFloat()-minX)*xDiv

//            Log.d(TAG, "findMatch: coord:$coord")
            if (x/coord <= 1){
                temp.add(i.get(0))
                temp.add(i.get(1))
                Log.d(TAG, "findMatch: coord:$coord")
                return temp
            }

        }

        return temp
    }

    //handle touch events
    override fun onTouchEvent(event: MotionEvent?): Boolean {

        performClick()

        val x = event?.getX(event.actionIndex)
        val y = event?.getY(event.actionIndex)

        with(event!!){

            when(event.actionMasked){

                MotionEvent.ACTION_DOWN->{
                    if (x!! < gw) {
//                    Log.d(TAG, "onTouchEvent: ${String.format("%.2f",findMatch(x!!).get(0))}")

//                        xCord = x
//                        yCord = y!!
                        findMatch(x).apply {
                            if (size != 0){
                                xCord =  (get(0).toFloat()-minX)*xDiv
                                yCord = (get(1).toFloat()-minY)*yDiv
                                viewPoint = true
                            }else {
                                viewPoint = false
                            }
                        }


                    }
                    return true
                }
                
                MotionEvent.ACTION_MOVE->{

//                    Log.d(TAG, "onTouchEvent: moved  ${x} , ${y}")
                    if (x!! < gw){

//                        xCord = x
//                        yCord = y!!
                        findMatch(x).apply {
                            if (size != 0){
                                xCord =  (get(0).toFloat()-minX)*xDiv
                                yCord = (get(1).toFloat()-minY)*yDiv
                                viewPoint = true
                            }else {
                                viewPoint = false
                                Log.d(TAG, "onTouchEvent: false")
                            }
                        }
                    }
//                    Log.d(TAG, "onTouchEvent: ${String.format("%.2f",findMatch(x!!).get(0))}")

                    return true
                }

                MotionEvent.ACTION_UP->{

                    viewPoint =false

                    return true
                }

                else -> {
//                    Log.d(TAG, "onTouchEvent: ${getX(actionIndex)} , ${getY(actionIndex)}")

                    return false
                }
            }

        }

    }

    override fun performClick(): Boolean {
        return super.performClick()
    }


}

