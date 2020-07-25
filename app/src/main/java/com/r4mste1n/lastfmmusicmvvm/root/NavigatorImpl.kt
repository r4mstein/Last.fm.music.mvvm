package com.r4mste1n.lastfmmusicmvvm.root

import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.r4mste1n.core_common.extensions.addFragment
import com.r4mste1n.core_common.extensions.replaceFragmentAndAddToBackStack
import com.r4mste1n.core_navigator_api.NavigatorApi
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
            containerId,
            ObjectGraph.getTopArtists().topArtistsStarter().get() as Fragment
        )
    }

    override fun showArtistInfoFragment(
        activity: AppCompatActivity,
        containerId: Int,
        artistName: String
    ) {
        activity.replaceFragmentAndAddToBackStack(
            containerId,
            ObjectGraph.getArtistInfo().artistInfoStarter().get(artistName) as Fragment
        )
    }

    override fun showHomeFragment(
        activity: AppCompatActivity,
        containerId: Int
    ) {
        activity.addFragment(
            containerId,
            ObjectGraph.getHomeFragment(
                listOf(
                    ObjectGraph.getTopArtists().topArtistsStarter().get() as Fragment,
                    ObjectGraph.getTopTracks().topTracksStarter().get() as Fragment
                )
            ) as Fragment
        )
    }

    override fun showTopTracksFragment(
        activity: AppCompatActivity,
        containerId: Int
    ) {
        activity.addFragment(
            containerId,
            ObjectGraph.getTopTracks().topTracksStarter().get() as Fragment
        )
    }

}