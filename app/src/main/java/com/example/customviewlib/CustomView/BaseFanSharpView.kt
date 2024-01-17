package com.example.customviewlib.CustomView

import android.annotation.SuppressLint
import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.graphics.Path
import android.graphics.RectF
import android.util.AttributeSet
import android.util.Log
import android.view.View
import com.example.customviewlib.R

/**
 * Created by lujialiang.2612 on 2023/12/19
 * @author lujialiang.2612@bytedance.com
 * desperate：封闭扇形
 */
@SuppressLint("Recycle")
class BaseFanSharpView @JvmOverloads constructor(
    mContext: Context, private var attrs: AttributeSet? = null, private var defStyle:Int = 0
): View(mContext,attrs,defStyle) {

    companion object {
        const val TAG = "mRadView"
        var draw_times = 0
    }

    //抗锯齿 开
    private var mPaint:Paint = Paint(Paint.ANTI_ALIAS_FLAG).apply {
        style = Paint.Style.FILL
        strokeCap = Paint.Cap.ROUND
    }
    //起始弧度 和 结束弧度
    private var startRad:Float = 0f
    private var endRad:Float = 0f
    private var currentEndRad = 0f
    private val mPath:Path = Path()

    private val mSize:RectF
        get() {
            return RectF(0f, 0f,width.toFloat(),height.toFloat())
        }
    private val mSizeWithPadding:RectF
        get() {
            return RectF(0f+paddingLeft,0f+paddingTop,
                width.toFloat()+paddingRight,height.toFloat()+paddingBottom)
        }

    //设置起止弧度
    fun setRadius(startRad:Float = 0f,endRad:Float = 0f) {
        this.startRad = startRad
        this.endRad = endRad
        mPath.apply {
            reset()
            addArc(mSizeWithPadding, this@BaseFanSharpView.startRad,(this@BaseFanSharpView.endRad-this@BaseFanSharpView.startRad))
            //close()
        }
        invalidate()
    }

    init {
        setWillNotDraw(false)
        //获取全体自定义属性
        val typedArray = context.obtainStyledAttributes(attrs, R.styleable.BaseFanSharpView)
        //设置起止弧度
        startRad = typedArray.getFloat(R.styleable.BaseFanSharpView_start_rad,0f)
        endRad = typedArray.getFloat(R.styleable.BaseFanSharpView_end_rad,0f)
        mPaint.color = typedArray.getColor(R.styleable.BaseFanSharpView_baseFanSharp_content_color, Color.BLACK)
    }

    override fun onMeasure(widthMeasureSpec: Int, heightMeasureSpec: Int) {
        val widMode = MeasureSpec.getMode(widthMeasureSpec)
        val widSize = MeasureSpec.getSize(widthMeasureSpec)

        val heightMode = MeasureSpec.getMode(heightMeasureSpec)
        val heightSize = MeasureSpec.getSize(heightMeasureSpec)

        when (widMode) {
            MeasureSpec.AT_MOST -> {

            }
            MeasureSpec.EXACTLY -> {

            }
            MeasureSpec.UNSPECIFIED -> {

            }
            else -> {

            }
        }

        when (heightMode) {

        }
        super.onMeasure(widthMeasureSpec, heightMeasureSpec)
    }

    override fun onDraw(canvas: Canvas) {
        super.onDraw(canvas)
        canvas.drawArc(mSizeWithPadding,startRad,endRad-startRad,true,mPaint)
        Log.d(TAG, "onDraw: times:${++draw_times}")
        Log.d(TAG, "onDraw: Finish + ${mSizeWithPadding.InfoString()}")
        Log.d(TAG, "onDraw: start Rad is $startRad,end Rad is $endRad")

    }

    //一个扇形增长动画，实现原理类似于scroller
    fun startGrowAni(endRad: Float) {
        //同步方法，上锁
        synchronized(this) {
            currentEndRad = endRad
            postDelayed(aniRunnable(),5)
        }
    }

    inner class aniRunnable:Runnable  {
        override fun run() {
            if (this@BaseFanSharpView.endRad < currentEndRad) {
                this@BaseFanSharpView.endRad += 0.5f
                invalidate()
                postDelayed(this,10)
            }
        }
    }


}

//test data
fun RectF.InfoString():String {
    return ("left is ${this.left},right is ${this.right}," +
            "top is ${this.top},bottom is ${this.bottom}")
}


    