package com.example.customviewlib.CustomView

import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.util.AttributeSet
import android.widget.FrameLayout

/**
 * Created by lujialiang.2612 on 2023/12/15
 * @author lujialiang.2612@bytedance.com
 * desperation:just a View to test mView
 */
class CanvasTestView @JvmOverloads constructor(
    mContext: Context,mAttrs:AttributeSet? = null,mDefStyle:Int = 0
) :FrameLayout(mContext,mAttrs,0){

    private val mPaint:Paint = Paint()

    init {
        mPaint.color = Color.RED
        mPaint.strokeWidth = 10f
    }

    override fun onDraw(canvas: Canvas) {
        super.onDraw(canvas)
        canvas.drawArc(0f,0f,200f,200f,0f,90f,true,mPaint)
    }
}