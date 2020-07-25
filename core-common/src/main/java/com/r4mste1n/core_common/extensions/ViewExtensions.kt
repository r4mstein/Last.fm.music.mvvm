package com.r4mste1n.core_common.extensions

import android.widget.ImageView
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions
import com.r4mste1n.core_common.GlideApp
import com.r4mste1n.core_common.R
import com.r4mste1n.core_common.extensions.ImageTransformType.*

/**
 * Created by Alex Shtain on 19.04.2020.
 */

sealed class ImageTransformType {
    object Default : ImageTransformType()
    object CenterCrop : ImageTransformType()
    object CircleCrop : ImageTransformType()
}

fun ImageView.loadImage(
    url: String,
    transformType: ImageTransformType = Default
) {
    val glideRequest = GlideApp.with(context).asDrawable()
        .placeholder(R.drawable.ic_image_placeholder)
        .transition(DrawableTransitionOptions.withCrossFade())

    when (transformType) {
        CenterCrop -> glideRequest.centerCrop()
        CircleCrop -> glideRequest.circleCrop()
    }

    glideRequest.load(url).into(this)
}