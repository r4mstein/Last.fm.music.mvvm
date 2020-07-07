package com.r4mste1n.lastfmmusicmvvm.root

import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.r4mste1n.core_common.extensions.addFragment
import com.r4mste1n.core_common.extensions.replaceFragmentAndAddToBackStack
import com.r4mste1n.core_navigator_api.NavigatorApi
import com.r4mste1n.feature_home_impl.R
import com.r4mste1n.lastfmmusicmvvm.root.di.ObjectGraph

/**
 * Created by Alex Shtain on 05.07.2020.
 */
class NavigatorImpl : NavigatorApi {

    override fun showTopArtistsFragment(
        activity: AppCompatActivity,
        containerId: Int
    ) {
        activity.addFragment(
            R.id.fragmentContainer,
            ObjectGraph.getTopArtists().topArtistsStarter().get() as Fragment
        )
    }

    override fun showArtistInfoFragment(
        activity: AppCompatActivity,
        containerId: Int,
        artistName: String
    ) {
        activity.replaceFragmentAndAddToBackStack(
            R.id.fragmentContainer,
            ObjectGraph.getArtistInfo().artistInfoStarter().get(artistName) as Fragment
        )
    }

}