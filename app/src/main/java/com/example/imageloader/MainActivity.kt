package com.example.imageloader

import android.os.Bundle
import android.widget.ImageView
import androidx.appcompat.app.AppCompatActivity
import com.example.themedemo.R
import com.peng.imageloader.loadImage

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val image = findViewById<ImageView>(R.id.ivMain)
        image.loadImage("https://devpress.csdnimg.cn/48ec5087d8f047fdbb47adcecd362186.jpg") {
            centerCrop = true
        }
    }
}