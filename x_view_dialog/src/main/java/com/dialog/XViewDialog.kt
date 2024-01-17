package com.dialog

import android.content.Context
import android.view.Gravity
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.FrameLayout


open class XViewDialog(context: Context, rootView: View? = null, layout: Int? = null) : ViewDialog,
    FrameLayout(context) {


    override fun cancel() {
        dismiss()
    }

    override fun dismiss() {
        if (parent != null) {
            (parent as ViewGroup).removeView(this)
        }
    }

    open fun level(): Int = 0
    open fun params(): LayoutParams {
        val layoutParams = LayoutParams(
            LayoutParams.WRAP_CONTENT,
            LayoutParams.WRAP_CONTENT
        )
        layoutParams.gravity = gravity()
        return layoutParams
    }

    open fun gravity() = Gravity.CENTER


    init {

        if (rootView == null && layout == null) {
            throw RuntimeException("必须选择传入布局或者view")

        }
        this.removeAllViews()

        if (rootView != null) {
            addView(rootView, params())

        } else {
            addView(
                LayoutInflater.from(context)
                    .inflate(layout!!, null), params()
            )


        }

        this.z = this.level().toFloat()

        if (this.isDark()) {
            this.setBackgroundColor("#4d000000".toColor())
        }
        safeClickListener {
            if (canceledOnTouchOutside()) {
                dismiss()
            }
        }

        this.initUI()

    }


    open fun initUI() {}


    open fun show() {
        val activity = context.findActivity()
        if (activity != null) {
            val root = activity.window.decorView.rootView

            var viewDialogContainer =
                root.findViewWithTag<XViewDialogContainer>(XViewDialogContainer::class.java.name)

            //还没容器就添加容器
            if (viewDialogContainer == null) {
                viewDialogContainer = XViewDialogContainer(context)
                (root as ViewGroup).addView(
                    viewDialogContainer,
                    ViewGroup.LayoutParams(
                        ViewGroup.LayoutParams.MATCH_PARENT,
                        ViewGroup.LayoutParams.MATCH_PARENT
                    )
                )
            }

            //显示添加
            viewDialogContainer.showDialog(this)

        }
    }


}