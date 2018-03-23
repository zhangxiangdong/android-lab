package me.zhang.workbench.ui.paint

import android.graphics.Bitmap
import android.graphics.Canvas
import android.graphics.Path
import android.view.MotionEvent

/**
 * Created by zhangxiangdong on 2018/3/22.
 */
class LinePainter(paintView: PaintView) : ShapePainter(paintView) {

    private val path = Path()
    private var preX = 0f
    private var preY = 0f

    override fun onDraw(canvas: Canvas, bitmapBuffer: Bitmap) {
        canvas.drawPath(path, getPaint())
        canvas.drawBitmap(bitmapBuffer, 0f, 0f, null)
    }

    override fun onTouchEvent(event: MotionEvent, bitmapCanvas: Canvas): Boolean {
        val x = event.x
        val y = event.y
        when (event.action) {
            MotionEvent.ACTION_DOWN -> {
                path.reset()
                preX = x
                preY = y
                path.moveTo(x, y)
            }
            MotionEvent.ACTION_MOVE -> {
                val controlX = (preX + x) / 2
                val controlY = (preY + y) / 2
                path.quadTo(controlX, controlY, x, y)
                invalidate()

                preX = x
                preY = y
            }
            MotionEvent.ACTION_UP -> {
                // 将手指移动的路径绘制到bitmapBuffer上
                bitmapCanvas.drawPath(path, getPaint())
                path.reset()
                invalidate()
            }
        }
        return true
    }

}