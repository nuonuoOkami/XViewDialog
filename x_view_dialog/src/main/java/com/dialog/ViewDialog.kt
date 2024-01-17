package com.dialog

import android.content.DialogInterface

interface  ViewDialog: DialogInterface {

    fun isDark() = true

    fun canceledOnTouchOutside() = false

    fun canceledAble() = false

}