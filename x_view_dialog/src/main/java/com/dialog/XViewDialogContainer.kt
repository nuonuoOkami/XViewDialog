package com.dialog

import android.content.Context
import android.util.AttributeSet
import android.view.KeyEvent
import android.view.View
import android.view.ViewGroup
import android.widget.FrameLayout
import androidx.core.view.children


class XViewDialogContainer : FrameLayout {


    constructor(context: Context) : super(context)
    constructor(context: Context, attrs: AttributeSet) : super(context, attrs)

    fun showDialog(child: ViewDialog) {


        val params =
            LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT)
        if (child.single()) {
            children
            val has = children.firstOrNull {
                it::class.java.name.equals(child::class.java.name)
            }
            if (has != null) {
                return
            } else {
                addView(child, params)
            }
        } else {
            addView(child, params)
        }


    }

    init {
        isFocusable = true;
        isFocusableInTouchMode = true;
        requestFocus()
        z = 65535f

        setOnKeyListener { _, keyCode, event ->
            if (keyCode == KeyEvent.KEYCODE_BACK && event.action == KeyEvent.ACTION_UP) {
                if (childCount > 0) {
                    //遍历第一个
                    val childZ = sortByZ()

                    val first = childZ.firstOrNull {
                        (it as IViewDialog).canceledAble()
                    }
                    if (first != null) {
                        (first as IViewDialog).dismiss()
                    }
                }

            }
            false

        }
    }

    override fun getChildDrawingOrder(childCount: Int, drawingPosition: Int): Int {
        val orderedViews: MutableList<View> = ArrayList()
        for (j in 0 until childCount) {
            val child = getChildAt(j)
            orderedViews.add(child)
        }

        orderedViews.sortWith { view1, view2 ->
            val zIndex1 = view1.z.toInt()
            val zIndex2 = view2.z.toInt()
            zIndex1.compareTo(zIndex2)
        }

        return indexOfChild(orderedViews[drawingPosition])
    }

    /**
     * z轴排序响应返回事件
     * @return MutableList<View>
     */

    private fun sortByZ(): MutableList<View> {
        val list = children.toMutableList();
        list.toMutableList().sortWith { a, b ->
            a.z.toInt().compareTo(b.z.toInt())
        }
        return list

    }

    init {
        tag=this::class.java.name
    }

}

