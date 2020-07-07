package com.r4mste1n.feature_home_api

/**
 * Created by Alex Shtain on 29.06.2020.
 */
interface HomeApi {

    fun setToolbarTitle(title: String)

    fun showTopArtistsFragment()

    fun showArtistInfoFragment(artistName: String)

    fun showProgressBar()

    fun hideProgressBar()

    fun showError(error: String)

    fun hideError()

}