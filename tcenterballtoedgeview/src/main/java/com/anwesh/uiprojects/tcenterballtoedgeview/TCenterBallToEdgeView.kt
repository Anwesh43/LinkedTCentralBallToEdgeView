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
