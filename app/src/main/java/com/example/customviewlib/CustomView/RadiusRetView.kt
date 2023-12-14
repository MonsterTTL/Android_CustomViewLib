package com.example.customviewlib.CustomView

import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.graphics.Path
import android.graphics.RectF
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

    //默认画笔
    private val DEFAULT_PAINT = Paint(Paint.ANTI_ALIAS_FLAG).apply {
        color = Color.GRAY
    }

    //画笔
    private var mPaint:Paint? = null
    private val mPath = Path()

    //一些携带的数据--携带的四方圆角数据
    //具体的数据映射：mRectF.left -> topLeft
    //             mRectF.top -> topRight
    //             mRectF.right -> bottomLeft
    //             mRectF.bottom -> bottomRight
    private val mRectF = RectF().apply { left = 20f
        right = 20f
        top = 20f
        bottom = 20f
    }

    //设置圆角弧度
    public fun setCornerRadius(topLeft:Float,topRight:Float,bottomLeft:Float,bottomRight:Float) {
        mRectF.set(topLeft,topRight,bottomLeft,bottomRight)
        invalidate()
    }

    public fun setCornerRadius(rectF:RectF) {
        mRectF.set(rectF)
        invalidate()
    }

    override fun onMeasure(widthMeasureSpec: Int, heightMeasureSpec: Int) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec)
    }


    override fun onDraw(canvas: Canvas) {
        super.onDraw(canvas)

        mPath.reset()
        mPath.addRoundRect(
            0F, 0F, width.toFloat(), height.toFloat(),
            floatArrayOf(mRectF.left,mRectF.left,mRectF.top,mRectF.top,
                mRectF.right,mRectF.right,mRectF.bottom,mRectF.bottom),
            Path.Direction.CW
            )

        canvas.drawPath(mPath,mPaint?:DEFAULT_PAINT)
        // feature:branch test 1
        //
    }
}