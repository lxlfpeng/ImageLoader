package com.peng.imageloader

/**
 * @Author : lxlfpeng
 * @Date   : 2022/3/10
 * @Email  : 565289282@qq.com
 * @Desc   : 使用策略对图片进行加载
 */
object ImageLoader : ImageLoaderStrategy {
    private var mStrategy: ImageLoaderStrategy? = null

    init {
        //默认使用Glide策略进行加载
        mStrategy = GlideImageLoader
    }

    /**
     * 更换不同策略框架对图片进行加载
     * @param imageLoaderStrategy:实现了图片加载的策略
     */
    fun setImageLoaderStrategy(imageLoaderStrategy: ImageLoaderStrategy) {
        mStrategy = imageLoaderStrategy
    }

    /**
     * 使用策略对图片进行加载
     */
    override fun loadImage(imageOptions: ImageOptions) {
        mStrategy?.loadImage(imageOptions)
    }
}