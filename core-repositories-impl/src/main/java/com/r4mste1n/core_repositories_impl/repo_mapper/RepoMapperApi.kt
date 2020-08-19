package com.r4mste1n.core_repositories_impl.repo_mapper

import com.r4mste1n.core_common.models.data.TopArtistData
import com.r4mste1n.core_common.models.data.TopTrackData
import com.r4mste1n.core_common.models.responses.TopArtistsResponse
import com.r4mste1n.core_common.models.responses.TopTracksResponse

/**
 * Created by Alex Shtain on 16.08.2020.
 */
interface RepoMapperApi {

    fun convertTopArtists(artists: TopArtistsResponse): List<TopArtistData>

    fun convertTopTracks(tracks: TopTracksResponse): List<TopTrackData>

}