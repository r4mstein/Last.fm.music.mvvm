package com.r4mste1n.feature_top_tracks_impl.starter

import com.r4mste1n.feature_top_tracks_api.TopTracksApi
import com.r4mste1n.feature_top_tracks_api.TopTracksStarterApi
import com.r4mste1n.feature_top_tracks_impl.TopTracksFragment
import javax.inject.Inject

/**
 * Created by Alex Shtain on 22.07.2020.
 */
class TopTracksStarterImpl @Inject constructor() : TopTracksStarterApi {

    override fun get(): TopTracksApi = TopTracksFragment.newInstance()

}