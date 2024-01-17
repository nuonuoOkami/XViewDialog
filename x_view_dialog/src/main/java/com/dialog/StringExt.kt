package com.dialog

import android.graphics.Color

fun String.toColor(): Int {


    return Color.parseColor(this);
}