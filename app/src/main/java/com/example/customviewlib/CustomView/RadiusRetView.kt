package com.example.customviewlib.CustomView

import android.content.Context
import android.graphics.Canvas
import android.util.AttributeSet
import android.widget.FrameLayout

/**
 * Created by lujialiang.2612 on 2023/12/13
 * @author lujialiang.2612@bytedance.com
 * despreation：一个四角可以设置为圆角的矩形View
 */
class RadiusRetView @JvmOverloads constructor(
    mContext: Context, attrs:AttributeSet? = null, defStyle:Int = 0
) : FrameLayout(mContext,attrs,defStyle) {

    //直接用@JvmOverloads秒了
    //constructor(mContext: Context, attrs:AttributeSet?):this(mContext,attrs,0)
    //constructor(mContext: Context):this(mContext,null)

    override fun onMeasure(widthMeasureSpec: Int, heightMeasureSpec: Int) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec)
    }


    override fun onDraw(canvas: Canvas) {
        super.onDraw(canvas)
        // feature:branch test 1
        //
    }
}