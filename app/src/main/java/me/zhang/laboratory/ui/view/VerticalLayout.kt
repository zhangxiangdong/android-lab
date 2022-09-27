package me.zhang.laboratory.ui.view

import android.content.Context
import android.util.AttributeSet
import android.view.ViewGroup

class VerticalLayout : ViewGroup {
    constructor(context: Context?) : this(context, null)
    constructor(context: Context?, attrs: AttributeSet?) : this(context, attrs, 0)
    constructor(context: Context?, attrs: AttributeSet?, defStyleAttr: Int) : super(
        context,
        attrs,
        defStyleAttr
    )

    override fun onMeasure(widthMeasureSpec: Int, heightMeasureSpec: Int) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec)
        measureChildren(widthMeasureSpec, heightMeasureSpec)

        var height = 0
        var width = 0
        for (i in 0 until childCount) {
            val child = getChildAt(i)

            val expectedWidth = child.measuredWidth + paddingStart + paddingEnd
            if (expectedWidth > width) {
                width = expectedWidth
            }

            height += child.measuredHeight
        }
        height += paddingTop + paddingBottom

        val wm = MeasureSpec.getMode(widthMeasureSpec)
        val ws = MeasureSpec.getSize(widthMeasureSpec)

        val hm = MeasureSpec.getMode(heightMeasureSpec)
        val hs = MeasureSpec.getSize(heightMeasureSpec)

        val wMs: Int
        val hMs: Int
        if (wm == MeasureSpec.AT_MOST && hm == MeasureSpec.AT_MOST) {
            wMs = width
            hMs = height
        } else if (wm == MeasureSpec.AT_MOST) {
            wMs = width
            hMs = hs
        } else if (hm == MeasureSpec.AT_MOST) {
            wMs = ws
            hMs = height
        } else {
            wMs = widthMeasureSpec
            hMs = heightMeasureSpec
        }

        setMeasuredDimension(wMs, hMs)
    }

    override fun onLayout(changed: Boolean, l: Int, t: Int, r: Int, b: Int) {
        var top = paddingTop
        val start = paddingStart
        for (i in 0 until childCount) {
            val child = getChildAt(i)
            child.layout(start, top, child.measuredWidth, top + child.measuredHeight)
            top += child.measuredHeight
        }
    }
}