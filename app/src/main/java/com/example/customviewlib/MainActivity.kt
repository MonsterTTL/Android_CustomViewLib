package com.example.customviewlib

import android.os.Build
import android.os.Bundle
import android.util.Log
import android.widget.SeekBar
import androidx.appcompat.app.AppCompatActivity
import com.example.customviewlib.databinding.ActivityMainBinding
import java.util.Locale

class MainActivity : AppCompatActivity() {

    companion object {
        private const val TAG = "MainActivity"
    }
    private lateinit var mBinding:ActivityMainBinding
    var Task: ((Int) -> Unit)? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mBinding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(mBinding.root)


        mBinding.seekBar2.max = 100
        mBinding.seekBar2.setOnSeekBarChangeListener(object : SeekBar.OnSeekBarChangeListener{

            override fun onProgressChanged(seekBar: SeekBar?, progress: Int, fromUser: Boolean) {
                mBinding.RadiusLineWithText.updateProgress(progress)
                Log.d(TAG, "onProgressChanged:${progress} ")
            }

            override fun onStartTrackingTouch(seekBar: SeekBar?) {

            }

            override fun onStopTrackingTouch(seekBar: SeekBar?) {

            }
        })


        val brand = Build.BRAND.toLowerCase(Locale.CHINA)

        Log.d(TAG, "brand ${brand}")
        //mBinding.mRadius.setCornerRadius( bottomLeft = 80f)
        //设置系统状态栏颜色为透明
        //window.statusBarColor = getColor(android.R.color.transparent)
        //window.decorView.systemUiVisibility = View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN or View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR
    }
}


