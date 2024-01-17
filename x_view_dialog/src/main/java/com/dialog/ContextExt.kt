package com.dialog

import android.app.Activity
import android.content.Context
import android.content.ContextWrapper
import android.util.DisplayMetrics
import android.view.WindowManager

fun Context.dp2px(num: Int): Int {
    return (resources.displayMetrics.density * num + 0.5f).toInt()
}

fun Context.dp2pxf(num: Int): Float {
    return (resources.displayMetrics.density * num + 0.5f)
}


fun Context.findActivity(): Activity? {
    return when (this) {
        is Activity -> {
            this
        }

        is ContextWrapper -> {
            findActivity()
        }

        else -> null

    }
}


fun Context.screenHeight(): Int {
    val metric = DisplayMetrics()
    val wm = getSystemService(Context.WINDOW_SERVICE) as WindowManager
    wm.defaultDisplay.getMetrics(metric)
    return metric.heightPixels
}

fun Context.screenWidth(): Int {
    val metric = DisplayMetrics()
    val wm = getSystemService(Context.WINDOW_SERVICE) as WindowManager
    wm.defaultDisplay.getMetrics(metric)
    return metric.widthPixels
}
