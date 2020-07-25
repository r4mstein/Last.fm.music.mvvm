package com.r4mste1n.feature_home_impl.homefragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.r4mste1n.core_common.listeners.SimplePagerPageChangeListener
import com.r4mste1n.feature_home_api.HomeApi
import com.r4mste1n.feature_home_api.HomeFragmentApi
import com.r4mste1n.feature_home_impl.R
import kotlinx.android.synthetic.main.fragment_home.*

/**
 * Created by Alex Shtain on 18.07.2020.
 */
class HomeFragment() : Fragment(), HomeFragmentApi {

    private lateinit var items: List<Fragment>

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_home, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setupUI()
    }

    private fun setupUI() {
        setupToolbarTitle(0)

        mainPager.apply {
            setPagingEnabled(false)
            adapter = HomePagerAdapter(childFragmentManager, items)
            bottomBar.setPager(this)

            addOnPageChangeListener(object : SimplePagerPageChangeListener() {
                override fun onPageSelected(position: Int) {
                    setupToolbarTitle(position)
                }
            })
        }
    }

    private fun setupToolbarTitle(position: Int) {
        (context as HomeApi).setToolbarTitle(
            requireContext().getString(
                when (position) {
                    0 -> R.string.top_artists_toolbar_title
                    1 -> R.string.top_songs_toolbar_title
                    else -> R.string.app_name
                }
            )
        )
    }

    companion object {

        fun newInstance(adapterItems: List<Fragment>) = HomeFragment().apply {
            items = adapterItems
        }

    }

}