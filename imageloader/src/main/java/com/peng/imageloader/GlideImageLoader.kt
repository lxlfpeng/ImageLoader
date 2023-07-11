package com.peng.imageloader

import android.annotation.SuppressLint
import android.content.Context
import android.graphics.drawable.Drawable
import android.widget.ImageView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.DataSource
import com.bumptech.glide.load.engine.GlideException
import com.bumptech.glide.load.resource.bitmap.BitmapTransformation
import com.bumptech.glide.load.resource.bitmap.CenterCrop
import com.bumptech.glide.request.RequestListener
import com.bumptech.glide.util.Preconditions
import com.peng.imageloader.transformation.BlurTransformation
import com.peng.imageloader.transformation.BorderTransformation
import com.peng.imageloader.transformation.CircleWithBorderTransformation
import com.peng.imageloader.transformation.GrayscaleTransformation
import com.peng.imageloader.transformation.RoundedCornersTransformation
import com.bumptech.glide.request.target.Target

/**
 * @Author : lxlfpeng
 * @Date   : 2022/3/10
 * @Email  : 565289282@qq.com
 * @Desc   : 这里是Glide实现图片加载的策略
 */
object GlideImageLoader : ImageLoaderStrategy {
    /**
     * 实现图片加载的具体逻辑功能
     */
    @SuppressLint("CheckResult")
    override fun loadImage(imageOptions: ImageOptions) {
        Preconditions.checkNotNull(imageOptions, "图片加载参数为空!")
        Preconditions.checkNotNull(imageOptions.context, "Context为空!")
        Preconditions.checkNotNull(imageOptions.imageView, "imageView为空!")
        Glide.with(imageOptions.context!!).load(imageOptions.imageRes).apply {
            diskCacheStrategy(imageOptions.diskCacheStrategy)
            priority(imageOptions.priority)
            skipMemoryCache(imageOptions.skipMemoryCache)
            //设置占位符
            if (imageOptions.placeHolderDrawable != null) {
                placeholder(imageOptions.placeHolderDrawable)
            } else if (imageOptions.placeHolderResId != 0) {
                placeholder(imageOptions.placeHolderResId)
            }
            //设置错误的图片
            if (imageOptions.errorDrawable != null) {
                error(imageOptions.errorDrawable)
            } else if (imageOptions.errorResId != 0) {
                error(imageOptions.errorResId)
            }
            //设置请求 url 为空图片
            if (imageOptions.fallbackDrawable != null) {
                fallback(imageOptions.fallbackDrawable)
            } else if (imageOptions.fallbackResId != 0) {
                fallback(imageOptions.fallbackResId)
            }
            //目标尺寸
            val size = imageOptions.size
            size?.let {
                override(size.width, size.height)
            }
            //解码格式
            format(imageOptions.format)
            //图片效果
            if (!imageOptions.isAnim) {
                dontAnimate()
            }
            if (imageOptions.centerCrop) {
                centerCrop()
            }
            if (imageOptions.isFitCenter) {
                fitCenter()
            }
            //圆形图片特效
            if (imageOptions.isCircle || imageOptions.circleBorderWidth > 0) {
                if (imageOptions.isCircle) {
                    transform(CircleWithBorderTransformation(imageOptions.circleBorderWidth, imageOptions.borderColor))
                } else {
                    transform(BorderTransformation(imageOptions.circleBorderWidth, imageOptions.borderColor))
                }
            }

            // 是否设置圆角特效
            if (imageOptions.isRoundedCorners) {
                var transformation: BitmapTransformation? = null
                // 圆角特效受到ImageView的scaleType属性影响
                val scaleType = imageOptions.imageView?.scaleType
                if (scaleType == ImageView.ScaleType.FIT_CENTER || scaleType == ImageView.ScaleType.CENTER_INSIDE ||
                    scaleType == ImageView.ScaleType.CENTER ||
                    scaleType == ImageView.ScaleType.CENTER_CROP
                ) {
                    transformation = CenterCrop()
                }
                if (transformation == null) {
                    transform(RoundedCornersTransformation(imageOptions.roundRadius, 0, imageOptions.cornerType))
                } else {
                    transform(transformation, RoundedCornersTransformation(imageOptions.roundRadius, 0, imageOptions.cornerType))
                }
            }

            if (imageOptions.isBlur) {
                transform(BlurTransformation(imageOptions.imageView!!.context, imageOptions.blurRadius, imageOptions.blurSampling))
            }

            if (imageOptions.isGray) {
                transform(GrayscaleTransformation())
            }

            imageOptions.transformation?.let {
                transform(*imageOptions.transformation!!)
            }

            //加载图片结果
            imageOptions.requestListener?.let {
                addListener(object : RequestListener<Drawable> {
                    override fun onLoadFailed(e: GlideException?, model: Any?, target: Target<Drawable>?, isFirstResource: Boolean): Boolean {
                        imageOptions.requestListener?.onFailAction?.invoke(e.toString())
                        return false
                    }

                    override fun onResourceReady(
                        resource: Drawable?,
                        model: Any?,
                        target: Target<Drawable>?,
                        dataSource: DataSource?,
                        isFirstResource: Boolean,
                    ): Boolean {
                        imageOptions.requestListener?.onSuccessAction?.invoke(resource)
                        return false
                    }
                })
            }

        }.into(imageOptions.imageView!!)
    }



    /*** 清除内存缓存*/
    @JvmStatic
    fun clearMemory(context: Context) {
        Glide.get(context).clearMemory();
    }

    /*** 取消图片加载*/
    @JvmStatic
    fun clearImage(context: Context, imageView: ImageView?) {
        Glide.get(context).requestManagerRetriever[context].clear(imageView!!)
    }

    /*** 预加载*/
    @JvmStatic
    fun preloadImage(context: Context, url: String?) {
        Glide.with(context).load(url).preload()
    }

    /*** 重新加载*/
    @JvmStatic
    fun resumeRequests(context: Context) {
        Glide.with(context).resumeRequests()
    }

    /*** 暂停加载*/
    @JvmStatic
    fun pauseRequests(context: Context) {
        Glide.with(context).pauseRequests()
    }
}