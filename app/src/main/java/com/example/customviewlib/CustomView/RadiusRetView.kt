package com.example.customviewlib.CustomView

import android.content.Context
import android.graphics.Canvas
import android.widget.FrameLayout

/**
 * Created by lujialiang.2612 on 2023/12/13
 * @author lujialiang.2612@bytedance.com
 * despreation：一个四角可以设置为圆角的矩形View
 */
class RadiusRetView(private val mContext: Context) : FrameLayout(mContext) {

    override fun onMeasure(widthMeasureSpec: Int, heightMeasureSpec: Int) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec)
    }


    override fun onDraw(canvas: Canvas) {
        super.onDraw(canvas)
    }
}