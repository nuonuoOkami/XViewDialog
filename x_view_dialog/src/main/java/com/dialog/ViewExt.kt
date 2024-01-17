package com.dialog

import android.content.Context
import android.graphics.Rect
import android.util.DisplayMetrics
import android.view.View
import android.view.Window
import android.view.WindowManager
import android.view.inputmethod.InputMethodManager

fun View.dp2px(num: Int): Int {
    return (resources.displayMetrics.density * num + 0.5f).toInt()
}

fun View.dp2pxf(num: Int): Float {
    return (resources.displayMetrics.density * num + 0.5f)
}

fun View.hideKeyBoard() {
    val imm = context
        .getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
    imm.hideSoftInputFromWindow(windowToken, 0)
}

fun View.showKeyBoard() {
    val imm = context
        .getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
    if (isSoftInputShown(context.findActivity()!!.window)) {
        imm.toggleSoftInput(0, 0)
    }
}

private fun isSoftInputShown(window: Window): Boolean {
    val decorView = window.decorView
    val screenHeight = decorView.height
    val rect = Rect()
    decorView.getWindowVisibleDisplayFrame(rect)
    return screenHeight - rect.bottom - getNavigateBarHeight(window.windowManager) >= 0
}

private fun getNavigateBarHeight(windowManager: WindowManager): Int {
    val metrics = DisplayMetrics()
    windowManager.defaultDisplay.getMetrics(metrics)
    val usableHeight = metrics.heightPixels
    windowManager.defaultDisplay.getRealMetrics(metrics)
    val realHeight = metrics.heightPixels
    return if (realHeight > usableHeight) {
        realHeight - usableHeight
    } else {
        0
    }
}


private const val MIN_CLICK_INTERVAL = 1000 // 双击间隔时间，单位为毫秒

fun View.safeClickListener(onSafeClick: (View) -> Unit) {
    var lastClickTime = 0L

    setOnClickListener { view ->
        val currentTime = System.currentTimeMillis()
        if (currentTime - lastClickTime >= MIN_CLICK_INTERVAL) {
            lastClickTime = currentTime
            onSafeClick(view)
        }
    }
}