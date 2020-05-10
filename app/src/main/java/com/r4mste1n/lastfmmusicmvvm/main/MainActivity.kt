package com.r4mste1n.lastfmmusicmvvm.main

import android.view.View
import android.widget.LinearLayout
import androidx.appcompat.widget.Toolbar
import com.google.android.material.bottomsheet.BottomSheetBehavior
import com.r4mste1n.lastfmmusicmvvm.R
import com.r4mste1n.lastfmmusicmvvm.main.artist_info.ArtistInfoFragment
import com.r4mste1n.lastfmmusicmvvm.main.top_artists.TopArtistsFragment
import com.r4mste1n.lastfmmusicmvvm.root.base.BaseActivity
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.error_layout.*

class MainActivity : BaseActivity() {

    private lateinit var errorBehavior: BottomSheetBehavior<LinearLayout>
    override val layout = R.layout.activity_main

    override fun setupUI() {
        setupToolbar()
        errorBehavior = BottomSheetBehavior.from(llErrorContainer).apply {
            state = BottomSheetBehavior.STATE_HIDDEN
        }

        showTopArtistsFragment()
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
        addFragment(R.id.flRootContainer, TopArtistsFragment.newInstance())
    }

    fun showArtistInfo(artistName: String) {
        replaceFragmentAndAddToBackStack(
            R.id.flRootContainer,
            ArtistInfoFragment.newInstance(artistName)
        )
    }

    fun showProgressBar() {
        if (pbLoader.visibility == View.GONE) pbLoader.visibility = View.VISIBLE
    }

    fun hideProgressBar() {
        if (pbLoader.visibility == View.VISIBLE) pbLoader.visibility = View.GONE
    }

    fun showError(error: String) {
        if (errorBehavior.state != BottomSheetBehavior.STATE_EXPANDED) {
            tvErrorMessage.text = error
            errorBehavior.state = BottomSheetBehavior.STATE_EXPANDED
        }
    }

    fun hideError() {
        if (errorBehavior.state != BottomSheetBehavior.STATE_HIDDEN) {
            errorBehavior.state = BottomSheetBehavior.STATE_HIDDEN
        }
    }

}
