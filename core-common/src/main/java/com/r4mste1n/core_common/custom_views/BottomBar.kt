package com.r4mste1n.core_common.custom_views

import android.content.Context
import android.os.Parcel
import android.os.Parcelable
import android.util.AttributeSet
import android.view.View
import android.view.View.OnClickListener
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.constraintlayout.widget.ConstraintSet
import androidx.viewpager.widget.ViewPager
import com.r4mste1n.core_common.R
import kotlinx.android.synthetic.main.bottom_bar.view.*


/**
 * Created by Alex Shtain on 12.07.2020.
 */
class BottomBar @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0
) : ConstraintLayout(context, attrs, defStyleAttr) {

    private var lastPosition = ARTISTS_POSITION
    private var pager: ViewPager? = null

    private val topSongsClickListener: OnClickListener = OnClickListener {
        topSongs.isEnabled = false
        topArtists.isEnabled = true

        lastPosition = SONGS_POSITION
        scrollPager(SONGS_POSITION)

        rootContainer.transitionToEnd()
    }

    private val topArtistsClickListener: OnClickListener = OnClickListener {
        topSongs.isEnabled = true
        topArtists.isEnabled = false

        lastPosition = ARTISTS_POSITION
        scrollPager(ARTISTS_POSITION)

        rootContainer.transitionToStart()
    }

    init {
        View.inflate(context, R.layout.bottom_bar, this)
        isSaveEnabled = true

        rootContainer.setOnClickListener { }
        topArtistsHeader.text = context.getString(R.string.top_artists_header)
        topSongsHeader.text = context.getString(R.string.top_songs_header)

        topSongs.setOnClickListener(topSongsClickListener)
        topArtists.setOnClickListener(topArtistsClickListener)
    }

    fun setPager(pagerAdapter: ViewPager) {
        pager = pagerAdapter
    }

    private fun scrollPager(position: Int) {
        pager?.let {
            handler.postDelayed({
                it.setCurrentItem(position, true)
            }, MOVING_ANIM_DURATION / 2)
        }
    }

    private fun restoreUIByPosition(position: Int) {
        ConstraintSet().apply {
            clone(rootContainer)

            when (position) {
                ARTISTS_POSITION -> {
                    rootContainer.progress = 0f
                }
                SONGS_POSITION -> {
                    rootContainer.progress = 1f
                }
            }
        }
    }

    companion object {

        private const val MOVING_ANIM_DURATION = 350L

        private const val ARTISTS_POSITION = 0
        private const val SONGS_POSITION = 1

    }

    override fun onSaveInstanceState(): Parcelable? {
        return SavedState(super.onSaveInstanceState()).apply {
            position = this@BottomBar.lastPosition
        }
    }

    override fun onRestoreInstanceState(state: Parcelable?) {
        val savedState = state as SavedState
        super.onRestoreInstanceState(savedState.superState)

        this.lastPosition = savedState.position
        restoreUIByPosition(lastPosition)
    }

    @Suppress("unused")
    private class SavedState : BaseSavedState {

        var position = 0

        internal constructor(superState: Parcelable?) : super(superState)

        private constructor(`in`: Parcel) : super(`in`) {
            position = `in`.readInt()
        }

        override fun writeToParcel(out: Parcel?, flags: Int) {
            super.writeToParcel(out, flags)

            out?.writeInt(position)
        }

        val CREATOR: Parcelable.Creator<SavedState> = object : Parcelable.Creator<SavedState> {
            override fun createFromParcel(`in`: Parcel): SavedState? {
                return SavedState(`in`)
            }

            override fun newArray(size: Int): Array<SavedState?> {
                return arrayOfNulls(size)
            }
        }

    }

}