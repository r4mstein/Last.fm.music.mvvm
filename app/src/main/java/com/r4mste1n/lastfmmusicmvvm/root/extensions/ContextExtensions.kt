package com.r4mste1n.lastfmmusicmvvm.root.extensions

import android.content.Context

/**
 * Created by Alex Shtain on 26.04.2020.
 */

fun Context.dpToPix(dp: Int): Float = dp * resources.displayMetrics.density

fun Context.spToPix(sp: Float): Float = sp * resources.displayMetrics.scaledDensity