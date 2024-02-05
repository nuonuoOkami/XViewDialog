package com.dialog

import android.content.DialogInterface

interface IViewDialog : DialogInterface {

    fun isDark() = true

    fun canceledOnTouchOutside() = false

    fun canceledAble() = false
    fun single() = true

}