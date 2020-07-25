package com.r4mste1n.core_common.custom_views

import android.content.Context
import android.util.AttributeSet
import android.view.MotionEvent
import androidx.viewpager.widget.ViewPager
import com.r4mste1n.core_common.listeners.SimplePagerPageChangeListener


/**
 * Created by Alex Shtain on 18.07.2020.
 */
class MainViewPager @JvmOverloads constructor(
    context: Context, attrs: AttributeSet? = null
) : ViewPager(context, attrs) {

    private var pagingEnabled = true
    var chosenPosition = 0

    init {
        addOnPageChangeListener(object : SimplePagerPageChangeListener() {
            override fun onPageSelected(position: Int) {
                chosenPosition = position
            }
        })
    }

    override fun onTouchEvent(event: MotionEvent?): Boolean {
        return when {
            pagingEnabled -> super.onTouchEvent(event)
            else -> false
        }
    }

    override fun onInterceptTouchEvent(event: MotionEvent?): Boolean {
        return when {
            pagingEnabled -> super.onInterceptTouchEvent(event)
            else -> false
        }
    }

    fun setPagingEnabled(enabled: Boolean) {
        pagingEnabled = enabled
    }

}