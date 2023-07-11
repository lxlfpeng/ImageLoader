package com.peng.imageloader

import android.widget.ImageView


/**
 * @Author : lxlfpeng
 * @Date   : 2022/3/10
 * @Email  : 565289282@qq.com
 * @Desc   : imageview扩展功能类
 */

/**
 * 加载图片
 */
fun ImageView.loadImage(load: Any?, options: (ImageOptions.() -> Unit)? = null) {
    val imageOptions = ImageOptions().also {
        it.imageRes = load
        it.imageView = this
    }
    ImageLoader.loadImage(options?.let {
        imageOptions.also(options)
    } ?: imageOptions)
}



