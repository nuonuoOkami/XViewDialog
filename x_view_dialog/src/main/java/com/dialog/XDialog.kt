package com.dialog

import android.app.Dialog
import android.content.Context
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.view.Gravity
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.WindowManager

/**
 * 基础dialog
 */
abstract class XDialog(context: Context) : Dialog(context) {


    init {
        val window = window
        window!!.decorView.setPadding(0, 0, 0, 0)
        val attributes = window.attributes

        window.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
        attributes.dimAmount = if (isDark()) 0.3f else 0f
        val option = View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN or View.SYSTEM_UI_FLAG_LAYOUT_STABLE
        window.decorView.systemUiVisibility = option
        window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS)
        window.statusBarColor = Color.TRANSPARENT
        //设置导航栏颜
        window.navigationBarColor = Color.TRANSPARENT
        //内容扩展到导航栏
        window.setType(WindowManager.LayoutParams.TYPE_APPLICATION_PANEL)
        window.attributes = attributes
        window.setGravity(gravity())
        window.setLayout(width(),height())
        setCanceledOnTouchOutside(outside())
        setCancelable(cancelable())

    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val view = LayoutInflater.from(context).inflate(layout(), null)
        setContentView(view)
        bind(view)

    }


    /**
     * @return 基础布局
     */
    protected abstract fun layout(): Int

    /**
     * 绑定
     *
     * @param view
     */
    open fun bind(view: View) {}



    open fun width(): Int {
        return ViewGroup.LayoutParams.WRAP_CONTENT
    }

    open fun height(): Int {
        return ViewGroup.LayoutParams.WRAP_CONTENT
    }

    /**
     * 点击外部是否可以消失
     *
     * @return
     */
    open fun outside(): Boolean {
        return true
    }

    /**
     * 是否可取消
     *
     * @return
     */
    open fun cancelable(): Boolean {
        return true
    }

    /**
     * 弹出是否变黑
     *
     * @return
     */
    open fun isDark(): Boolean {
        return false
    }


    open fun gravity(): Int {
        return Gravity.CENTER
    }


}