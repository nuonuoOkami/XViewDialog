package com.dialog

import android.content.Context
import android.view.Gravity
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.FrameLayout


open class ViewDialog(context: Context, rootView: View? = null, layout: Int? = null) : IViewDialog,
    ViewDialogBusiness,
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


        if (rootView != null) {
          addView(rootView, params())
            bindUI(rootView)

        } else {
            val root = LayoutInflater.from(context)
                .inflate(layout!!, null)
            addView(
                root, params()
            )

            bindUI(root)


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


    }


    open fun bindUI(rootView: View) {

    }


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

    override fun business() {

    }


}