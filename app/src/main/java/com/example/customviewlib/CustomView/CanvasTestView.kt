package com.example.customviewlib.CustomView

import android.annotation.SuppressLint
import android.content.Context
import android.graphics.BitmapFactory
import android.graphics.BitmapShader
import android.graphics.BlurMaskFilter
import android.graphics.Camera
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.LinearGradient
import android.graphics.Paint
import android.graphics.RectF
import android.graphics.Shader
import android.util.AttributeSet
import android.view.View
import com.example.customviewlib.R
import kotlin.math.min

/**
 * Created by lujialiang.2612 on 2023/12/15
 * @author lujialiang.2612@bytedance.com
 * desperation:just a View to test mView
 */
class CanvasTestView @JvmOverloads constructor(
    mContext: Context,mAttrs:AttributeSet? = null,mDefStyle:Int = 0
) :View(mContext,mAttrs,0){

    private val mPaint:Paint = Paint()
    private val mCamera = Camera()
    var is2D_Rotate = true
    var degree_Rotate = 0

    private val rectF
        get() = RectF(0f,0f,width.toFloat(),height.toFloat())

    init {
        setWillNotDraw(false)
        mPaint.color = Color.RED
        mPaint.strokeWidth = 10f
    }

    @SuppressLint("DrawAllocation")
    override fun onDraw(canvas: Canvas) {
        super.onDraw(canvas)
        val shade = LinearGradient(0f,0f,width.toFloat(),height.toFloat(),Color.BLUE,
            resources.getColor(android.R.color.transparent), Shader.TileMode.CLAMP)

        val mBitmap = BitmapFactory.decodeResource(resources,R.drawable.images)
        val bitmapShader = BitmapShader(mBitmap,Shader.TileMode.MIRROR,Shader.TileMode.MIRROR)

        mPaint.shader = shade
        canvas.drawRect(rectF,mPaint)
        mPaint.shader = bitmapShader
        mPaint.maskFilter = BlurMaskFilter(50f,BlurMaskFilter.Blur.NORMAL)

        canvas.save()
        if (is2D_Rotate) {
            canvas.rotate(degree_Rotate.toFloat(),width.toFloat()/2,height.toFloat()/2)
        } else {
            mCamera.apply {
                save()
                rotateX(degree_Rotate.toFloat())
                canvas.translate(width.toFloat()/2,height.toFloat()/2)
                //translate(-width.toFloat()/2,height.toFloat()/2,0f)
                applyToCanvas(canvas)
                //translate(width.toFloat()/2,-height.toFloat()/2,0f)
                canvas.translate(-width.toFloat()/2,-height.toFloat()/2)
                restore()
            }
        }
        canvas.drawCircle(width.toFloat()/2,height.toFloat()/2, min(width,height).toFloat()/3,mPaint)
        canvas.restore()

        //canvas.drawArc(rectF,0f,90f,true,mPaint)
    }




}