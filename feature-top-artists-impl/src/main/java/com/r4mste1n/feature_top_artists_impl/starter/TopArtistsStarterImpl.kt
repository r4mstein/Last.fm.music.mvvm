package com.r4mste1n.feature_top_artists_impl.starter

import com.r4mste1n.feature_top_artists_api.TopArtistsApi
import com.r4mste1n.feature_top_artists_api.TopArtistsStarterApi
import com.r4mste1n.feature_top_artists_impl.TopArtistsFragment
import javax.inject.Inject

/**
 * Created by Alex Shtain on 29.06.2020.
 */
class TopArtistsStarterImpl @Inject constructor() : TopArtistsStarterApi {

    override fun get(): TopArtistsApi = TopArtistsFragment.newInstance()

}