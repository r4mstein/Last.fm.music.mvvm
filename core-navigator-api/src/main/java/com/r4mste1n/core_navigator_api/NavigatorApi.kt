package com.r4mste1n.core_navigator_api

import androidx.appcompat.app.AppCompatActivity

/**
 * Created by Alex Shtain on 05.07.2020.
 */
interface NavigatorApi {

    fun showTopArtistsFragment(
        activity: AppCompatActivity,
        containerId: Int
    )

    fun showArtistInfoFragment(
        activity: AppCompatActivity,
        containerId: Int,
        artistName: String
    )

}