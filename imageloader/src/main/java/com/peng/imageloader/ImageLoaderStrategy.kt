package com.peng.imageloader

/**
 * @Author : lxlfpeng
 * @Date   : 2022/3/10
 * @Email  : 565289282@qq.com
 * @Desc   : 用于图片加载策略的接口 glide Picasso Fresco可以实现该接口做对应的策略处理
 */
interface ImageLoaderStrategy {
    /**
     * 提供一个加载图片的方法
     * @param imageOptions 图片加载的参数功能类
     */
    fun loadImage(imageOptions: ImageOptions)
}