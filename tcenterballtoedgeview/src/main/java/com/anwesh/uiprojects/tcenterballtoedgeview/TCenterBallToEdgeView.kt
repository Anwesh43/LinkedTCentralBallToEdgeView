package com.anwesh.uiprojects.tcenterballtoedgeview

/**
 * Created by anweshmishra on 27/08/20.
 */

import android.view.View
import android.view.MotionEvent
import android.app.Activity
import android.content.Context
import android.graphics.Paint
import android.graphics.Color
import android.graphics.Canvas

val colors : Array<Int> = arrayOf(Color.CYAN, Color.RED, Color.BLUE, Color.GREEN, Color.MAGENTA)
val strokeFactor : Float = 90f
val sizeFactor : Float = 2.9f
val parts : Int = 4
val scGap : Float = 0.02f / parts
val foreColor : Int = Color.parseColor("#BDBDBD")
val delay : Long = 20
val rot : Float = 90f
val lines : Int = 3
val rFactor : Float = 6.2f

fun Int.inverse() : Float = 1f / this
fun Float.maxScale(i : Int, n : Int) : Float = Math.max(0f, this - i * n.inverse())
fun Float.divideScale(i : Int, n : Int) : Float = Math.min(n.inverse(), maxScale(i, n)) * n
fun Float.sinify() : Float = Math.sin(this * Math.PI).toFloat()

fun Canvas.drawTCenterBallToEdge(scale : Float, w : Float, h : Float, paint : Paint) {
    val sf : Float = scale.sinify()
    val w : Float = width.toFloat()
    val h : Float = height.toFloat()
    val size : Float = Math.min(w, h) / sizeFactor
    val sf1 : Float = scale.divideScale(0, parts)
    val sf2 : Float = scale.divideScale(1, parts)
    val sf3 : Float = scale.divideScale(2, parts)
    val sf4 : Float = scale.divideScale(3, parts)
    val r : Float = size / rFactor
    save()
    translate(w / 2, h / 2)
    for (j in 0..(lines - 1)) {
        save()
        rotate(rot * sf2)
        drawLine(0f, 0f, size * sf1 + size * (j % 2) * sf2, 0f, paint)
        drawCircle(size * sf4, 0f, r * sf3, paint)
        restore()
    }
    restore()
}

fun Canvas.drawTCBENode(i : Int, scale : Float, paint : Paint) {
    val w : Float = width.toFloat()
    val h : Float = height.toFloat()
    paint.color = foreColor
    paint.strokeCap = Paint.Cap.ROUND
    paint.strokeWidth = Math.min(w, h) / strokeFactor
    drawTCenterBallToEdge(scale, w, h, paint)
}

class TCenterBallToEdgeView(ctx : Context) : View(ctx) {

    override fun onDraw(canvas : Canvas) {

    }

    override fun onTouchEvent(event : MotionEvent) : Boolean {
        when (event.action) {
            MotionEvent.ACTION_DOWN -> {

            }
        }
        return true
    }

    data class State(var scale : Float = 0f, var dir : Float = 0f, var prevScale : Float = 0f) {

        fun update(cb : (Float) -> Unit) {
            scale += scGap * dir
            if (Math.abs(scale - prevScale) > 1) {
                scale = prevScale + dir
                dir = 0f
                prevScale = scale
                cb(prevScale)
            }
        }

        fun startUpdating(cb : () -> Unit) {
            if (dir == 0f) {
                dir = 1f - 2 * prevScale
                cb()
            }
        }
    }
}