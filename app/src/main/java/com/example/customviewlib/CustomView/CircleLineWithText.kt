package com.example.customviewlib.CustomView

import android.annotation.SuppressLint
import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.graphics.RectF
import android.graphics.SweepGradient
import android.util.AttributeSet
import android.util.Log
import android.view.View
import com.example.customviewlib.R
import kotlin.math.min

/**
 * Created by lujialiang.2612 on 2023/12/21
 * @author lujialiang.2612@bytedance.com
 */
class CircleLineWithText @JvmOverloads constructor(
    mContext: Context, attributeSet: AttributeSet? = null, defStyle:Int = 0
):View(mContext,attributeSet,defStyle) {



    companion object {
        private const val TAG = "CircleLineWithText"
        private const val Debug = true
    }
    var contentString = "Enjoy Your Life Cmf"
    private val Color_TransParent
        get() = resources.getColor(android.R.color.transparent)

    var mTextColor = Color.BLACK
    var mLineColor = Color.BLACK
    var mTextSize = 18f
    var currentRad = 0f


    init {
        val typeArray = mContext.obtainStyledAttributes(attributeSet, R.styleable.CircleLineWithText)
        mTextColor = typeArray.getColor(R.styleable.CircleLineWithText_text_Color,Color.BLACK)
        mLineColor = typeArray.getColor(R.styleable.CircleLineWithText_line_Color,Color.BLACK)
        mTextSize = typeArray.getFloat(R.styleable.CircleLineWithText_text_Size,18f)
        currentRad = typeArray.getFloat(R.styleable.CircleLineWithText_current_Radius,0f)
    }

    private val mLinePaint = Paint(Paint.ANTI_ALIAS_FLAG).apply {
        strokeCap = Paint.Cap.ROUND
        style = Paint.Style.STROKE
        strokeWidth = 30f
        color = Color_TransParent
    }

    private val mTextPaint = Paint(Paint.ANTI_ALIAS_FLAG).apply {
        color = Color_TransParent
        style = Paint.Style.STROKE
        strokeWidth = 0f
    }

    val mSizeWithPadding
        get() = RectF(0f+paddingLeft,0f+paddingTop,
            width.toFloat()-paddingRight-paddingLeft,height.toFloat()-paddingBottom-paddingTop)

    private val contentWidth
        get() = width-paddingLeft-paddingRight

    private val contentHeight
        get() = height-paddingTop-paddingBottom

    fun updateProgress(progressIn100:Int) {
        val tragetRad = progressIn100 * 360 / 100
        currentRad = tragetRad.toFloat()
        invalidate()
    }

    @SuppressLint("DrawAllocation")
    override fun onDraw(canvas: Canvas) {
        super.onDraw(canvas)
        val shader = SweepGradient((paddingLeft+contentWidth/2-paddingRight).toFloat(),
            (paddingTop+contentHeight/2-paddingBottom).toFloat(),
                mLineColor,
                Color.RED
            )

        mLinePaint.color = mLineColor
        mLinePaint.shader = shader
        Log.d(TAG, "onDraw: x:${contentWidth},y:${contentHeight},r:${min(contentWidth,contentHeight).toFloat()/2}")
        canvas.drawArc(paddingLeft.toFloat()+mLinePaint.strokeWidth,
            paddingTop.toFloat()+mLinePaint.strokeWidth,
            paddingLeft+min(contentHeight,contentWidth).toFloat()-mLinePaint.strokeWidth-paddingRight,
            paddingTop+min(contentHeight,contentWidth).toFloat()-mLinePaint.strokeWidth-paddingBottom,
            0f,currentRad,false,mLinePaint
            )


        mTextPaint.color = mTextColor
        mTextPaint.textSize = mTextSize
        mTextPaint.style = Paint.Style.FILL
        mTextPaint.isUnderlineText = true
        contentString = (currentRad/360*100).toInt().toString() + "%"
        val lengthOfString = contentString.length * 25
        canvas.drawText(contentString,
            min(contentWidth,contentHeight).toFloat()/2-lengthOfString/2,
            min(contentWidth,contentHeight).toFloat()/2+lengthOfString/10,
            mTextPaint)

        mTextPaint.style = Paint.Style.STROKE

        if (Debug) {
            canvas.drawRect(mSizeWithPadding,mTextPaint)
            canvas.drawRect(paddingLeft.toFloat()+mLinePaint.strokeWidth,
                paddingTop.toFloat()+mLinePaint.strokeWidth,
                paddingLeft+min(contentHeight,contentWidth).toFloat()-mLinePaint.strokeWidth-paddingRight,
                paddingTop+min(contentHeight,contentWidth).toFloat()-mLinePaint.strokeWidth-paddingBottom,
                mTextPaint)
        }

    }
}