package com.r4mste1n.feature_home_impl

import android.view.View
import android.widget.LinearLayout
import androidx.appcompat.widget.Toolbar
import com.google.android.material.bottomsheet.BottomSheetBehavior
import com.r4mste1n.core_common.base.BaseActivity
import com.r4mste1n.core_navigator_api.NavigatorApi
import com.r4mste1n.feature_home_api.HomeApi
import com.r4mste1n.feature_home_impl.di.DiHomeComponent
import kotlinx.android.synthetic.main.activity_home.*
import kotlinx.android.synthetic.main.error_layout.*
import javax.inject.Inject

/**
 * Created by Alex Shtain on 29.06.2020.
 */
class HomeActivity : BaseActivity(), HomeApi {

    private lateinit var errorBehavior: BottomSheetBehavior<LinearLayout>
    override val layout = R.layout.activity_home

    @Inject
    lateinit var navigatorApi: NavigatorApi

    override fun setupUI() {

        DiHomeComponent.get().inject(this)

        setupToolbar()
        errorBehavior = BottomSheetBehavior.from(errorContainer).apply {
            state = BottomSheetBehavior.STATE_HIDDEN
        }

        if (supportFragmentManager.fragments.size == 0) {
            navigatorApi.showHomeFragment(this, R.id.fragmentContainer)
        }
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

    override fun showTopArtistsFragment() {
        navigatorApi.showTopArtistsFragment(this, R.id.fragmentContainer)
    }

    override fun showArtistInfoFragment(artistName: String) {
        navigatorApi.showArtistInfoFragment(this, R.id.fragmentContainer, artistName)
    }

    override fun setToolbarTitle(title: String) {
        supportActionBar?.title = title
    }

    override fun showProgressBar() {
        if (loader.visibility == View.GONE) {
            loader.visibility = View.VISIBLE
        }
    }

    override fun hideProgressBar() {
        if (loader.visibility == View.VISIBLE) {
            loader.visibility = View.GONE
        }
    }

    override fun showError(error: String) {
        if (errorBehavior.state != BottomSheetBehavior.STATE_EXPANDED) {
            errorMessage.text = error
            errorBehavior.state = BottomSheetBehavior.STATE_EXPANDED
        }
    }

    override fun hideError() {
        if (errorBehavior.state != BottomSheetBehavior.STATE_HIDDEN) {
            errorBehavior.state = BottomSheetBehavior.STATE_HIDDEN
        }
    }

}