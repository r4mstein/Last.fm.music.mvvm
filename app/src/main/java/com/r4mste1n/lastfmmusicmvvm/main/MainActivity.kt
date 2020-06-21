package com.r4mste1n.lastfmmusicmvvm.main

import android.view.View
import android.widget.LinearLayout
import androidx.appcompat.widget.Toolbar
import com.google.android.material.bottomsheet.BottomSheetBehavior
import com.r4mste1n.lastfmmusicmvvm.R
import com.r4mste1n.lastfmmusicmvvm.main.artist_info.ArtistInfoFragment
import com.r4mste1n.lastfmmusicmvvm.main.top_artists.TopArtistsFragment
import com.r4mste1n.lastfmmusicmvvm.root.base.BaseActivity
import com.r4mste1n.lastfmmusicmvvm.root.di.ObjectGraph
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.error_layout.*

class MainActivity : BaseActivity() {

    private lateinit var errorBehavior: BottomSheetBehavior<LinearLayout>
    override val layout = R.layout.activity_main

    override fun setupUI() {

        ObjectGraph.getMainComponent().inject(this)

        setupToolbar()
        errorBehavior = BottomSheetBehavior.from(errorContainer).apply {
            state = BottomSheetBehavior.STATE_HIDDEN
        }

        if (supportFragmentManager.fragments.size == 0) showTopArtistsFragment()
    }

    override fun onDestroy() {
        super.onDestroy()
        ObjectGraph.destroyMainComponent()
    }

    private fun setupToolbar() {
        setSupportActionBar(toolbar as Toolbar)
        supportActionBar?.setDisplayShowHomeEnabled(true)
        supportFragmentManager.addOnBackStackChangedListener {
            supportActionBar?.setDisplayHomeAsUpEnabled(supportFragmentManager.backStackEntryCount > 0)
        }
    }

    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return true
    }

    fun showTopArtistsFragment() {
        addFragment(R.id.fragmentContainer, TopArtistsFragment.newInstance())
    }

    fun showArtistInfo(artistName: String) {
        replaceFragmentAndAddToBackStack(
            R.id.fragmentContainer,
            ArtistInfoFragment.newInstance(artistName)
        )
    }

    fun showProgressBar() {
        if (loader.visibility == View.GONE) {
            loader.visibility = View.VISIBLE
        }
    }

    fun hideProgressBar() {
        if (loader.visibility == View.VISIBLE) {
            loader.visibility = View.GONE
        }
    }

    fun showError(error: String) {
        if (errorBehavior.state != BottomSheetBehavior.STATE_EXPANDED) {
            errorMessage.text = error
            errorBehavior.state = BottomSheetBehavior.STATE_EXPANDED
        }
    }

    fun hideError() {
        if (errorBehavior.state != BottomSheetBehavior.STATE_HIDDEN) {
            errorBehavior.state = BottomSheetBehavior.STATE_HIDDEN
        }
    }

}
