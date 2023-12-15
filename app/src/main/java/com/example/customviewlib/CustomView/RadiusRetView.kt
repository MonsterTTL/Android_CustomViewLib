package com.example.customviewlib.CustomView

import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.graphics.Path
import android.graphics.drawable.ColorDrawable
import android.util.AttributeSet
import android.util.Log
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
    companion object {
        const val TAG = "RadiusRetView"
        //默认画笔
        private val DEFAULT_PAINT = Paint(Paint.ANTI_ALIAS_FLAG).apply {
            color = Color.GRAY
        }
    }


    //画笔
    private var mPaint:Paint? = null
    private val mPath = Path()
    init {
        Log.d(TAG, "init")
        if (background is ColorDrawable) {
            mPaint = Paint(Paint.ANTI_ALIAS_FLAG).apply {
                color = (background as ColorDrawable).color
            }
            (background as ColorDrawable).color = resources.getColor(android.R.color.transparent)
        }
        //invalidate()
    }

    //四角圆角的dp值
    private var Radius_TopLeft = 0f
    private var Radius_TopRight = 0f
    private var Radius_BottomLeft = 0f
    private var Radius_BottomRight = 0f

    //设置圆角弧度
    public fun setCornerRadius(topLeft: Float = 0f, topRight:Float = 0f, bottomLeft:Float = 0f, bottomRight:Float = 0f) {
        Radius_TopLeft = topLeft
        Radius_TopRight = topRight
        Radius_BottomLeft = bottomLeft
        Radius_BottomRight = bottomRight
        invalidate()
    }


    override fun onMeasure(widthMeasureSpec: Int, heightMeasureSpec: Int) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec)
    }


    override fun onDraw(canvas: Canvas) {
        super.onDraw(canvas)
        //绘制圆角图形的边界 画笔默认先顺时针方向吧
        mPath.reset()
        mPath.addRoundRect(0f,0f,width.toFloat(),height.toFloat(),
            floatArrayOf(Radius_TopLeft,Radius_TopLeft,Radius_TopRight,Radius_TopRight,
                Radius_BottomLeft,Radius_BottomLeft,Radius_BottomRight,Radius_BottomRight
            ),Path.Direction.CW
        )

        //mPath.addArc()
        Log.d(TAG, "onDraw: ${mPaint}")
        val usedPaint = mPaint?:DEFAULT_PAINT

        //test code
        //usedPaint.style = Paint.Style.STROKE
        //usedPaint.strokeWidth = 10f
        //test code

        canvas.drawPath(mPath,usedPaint)
    }
}