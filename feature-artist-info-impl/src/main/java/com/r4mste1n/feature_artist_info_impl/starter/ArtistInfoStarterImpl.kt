package com.r4mste1n.feature_artist_info_impl.starter

import com.r4mste1n.feature_artist_info_api.ArtistInfoApi
import com.r4mste1n.feature_artist_info_api.ArtistInfoStarterApi
import com.r4mste1n.feature_artist_info_impl.ArtistInfoFragment
import javax.inject.Inject

/**
 * Created by Alex Shtain on 05.07.2020.
 */
class ArtistInfoStarterImpl @Inject constructor() : ArtistInfoStarterApi {

    override fun get(artistName: String): ArtistInfoApi = ArtistInfoFragment.newInstance(artistName)

}