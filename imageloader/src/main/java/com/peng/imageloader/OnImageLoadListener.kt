package com.peng.imageloader

import android.graphics.drawable.Drawable

/**
 * @Author : lxlfpeng
 * @Date   : 2022/3/10
 * @Email  : 565289282@qq.com
 * @Desc   : 图片加载结果回调
 */
class OnImageLoadListener {
    internal var onFailAction: ((String?) -> Unit)? = null
    internal var onSuccessAction: ((Drawable?) -> Unit)? = null

    /**
     *加载错误
     */
    fun onFail(action: (String?) -> Unit) {
        onFailAction = action
    }

    /**
     *加载成功
     */
    fun onSuccess(action: (Drawable?) -> Unit) {
        onSuccessAction = action
    }
}