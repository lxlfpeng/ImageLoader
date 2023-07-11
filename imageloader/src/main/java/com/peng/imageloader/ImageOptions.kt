package com.peng.imageloader

import android.content.Context
import android.graphics.Bitmap
import android.graphics.Color
import android.graphics.drawable.Drawable
import android.widget.ImageView
import androidx.annotation.ColorInt
import androidx.annotation.DrawableRes
import com.bumptech.glide.Priority
import com.bumptech.glide.load.DecodeFormat
import com.bumptech.glide.load.Transformation
import com.bumptech.glide.load.engine.DiskCacheStrategy

/***
 * @Author : lxlfpeng
 * @Date   : 2022/3/10
 * @Email  : 565289282@qq.com
 * @Desc   : 用户图片加载的选项
 */
class ImageOptions {
    /***加载图片的路径*/
    var imageRes: Any? = null

    /***加载图片的容器*/
    var imageView: ImageView? = null

    /***加载图片的上下文 */
    var context: Context? = null
        get() {
            return imageView?.context
        }

    /***占位图资源*/
    @DrawableRes
    var placeHolderResId: Int = 0
    var placeHolderDrawable: Drawable? = null

    /***错误占位图*/
    @DrawableRes
    var errorResId: Int = 0
    var errorDrawable: Drawable? = null

    /***请求的url/model为 null 的时候展示的图片 （如果没有设置，还是展示placeholder的占位符） */
    @DrawableRes
    var fallbackResId: Int = 0
    var fallbackDrawable: Drawable? = null

    /***是否跳过内存缓存*/
    var skipMemoryCache: Boolean = false

    /***加载是否有动画，默认是使用动画*/
    var isAnim: Boolean = true

    /***磁盘缓存策略*/
    var diskCacheStrategy = DiskCacheStrategy.AUTOMATIC

    /***加载优先级*/
    var priority = Priority.NORMAL

    /***加载图片的尺寸*/
    var size: OverrideSize? = null

    /***是否加载成圆形图片*/
    var isCircle: Boolean = false

    /***圆形图片边框宽度*/
    var circleBorderWidth: Int = 0

    /***圆形图片边框颜色*/
    @ColorInt
    var borderColor: Int = Color.TRANSPARENT

    /***是否加载成灰色图片*/
    var isGray: Boolean = false

    /***是否中心裁剪*/
    var centerCrop: Boolean = false

    /***是否FitCenter裁剪*/
    var isFitCenter: Boolean = false

    /***加载图片的编码格式*/
    var format: DecodeFormat = DecodeFormat.DEFAULT

    /***是否启用高斯模糊效果*/
    var isBlur: Boolean = false

    /***高斯模糊半經 0～25*/
    var blurRadius: Int = 25

    /***缩放系数 加速模糊速度 0～25*/
    var blurSampling: Int = 4

    /***是否加载成圆角图片*/
    var isRoundedCorners: Boolean = false

    /***圆角的弧度*/
    var roundRadius: Int = 0

    /***圆角的边向*/
    var cornerType: CornerType = CornerType.ALL

    enum class CornerType {
        ALL, TOP_LEFT, TOP_RIGHT, BOTTOM_LEFT, BOTTOM_RIGHT, TOP, BOTTOM, LEFT, RIGHT, OTHER_TOP_LEFT, OTHER_TOP_RIGHT, OTHER_BOTTOM_LEFT, OTHER_BOTTOM_RIGHT, DIAGONAL_FROM_TOP_LEFT, DIAGONAL_FROM_TOP_RIGHT
    }

    /***图片转换器*/
    var transformation: Array<out Transformation<Bitmap>>? = null

    /***加载监听*/
    var requestListener: OnImageLoadListener? = null
        private set

    /***设置加载监听*/
    fun requestListener(listener: OnImageLoadListener.() -> Unit) {
        requestListener = OnImageLoadListener().also(listener)
    }

    /***
     * 指定加载图片的大小
     * @param width 宽
     * @param height 高
     */
    class OverrideSize(val width: Int, val height: Int)
}