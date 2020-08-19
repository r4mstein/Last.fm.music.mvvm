package com.r4mste1n.core_db_api

import androidx.lifecycle.LiveData
import com.r4mste1n.core_common.models.data.TopArtistData
import com.r4mste1n.core_common.models.data.TopTrackData
import com.r4mste1n.core_common.models.responses.TopArtistsResponse
import com.r4mste1n.core_common.models.responses.TopTracksResponse

/**
 * Created by Alex Shtain on 12.08.2020.
 */
interface DBManagerApi {

    suspend fun insertTopArtists(data: TopArtistsResponse): Boolean

    suspend fun getTopArtists(): LiveData<List<TopArtistData>>

    suspend fun clearAllTopArtists()

    suspend fun insertTopTracks(data: TopTracksResponse): Boolean

    suspend fun getTopTracks(): LiveData<List<TopTrackData>>

    suspend fun clearAllTopTracks()

}