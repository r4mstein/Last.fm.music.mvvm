package com.r4mste1n.lastfmmusicmvvm.main.repositories.top_artists

import com.r4mste1n.lastfmmusicmvvm.main.top_artists.models.TopArtistsResponse
import com.r4mste1n.lastfmmusicmvvm.root.network.Result

/**
 * Created by Alex Shtain on 03.05.2020.
 */
interface TopArtistsRepositoryContract {

    suspend fun loadTopArtists(listener: (Result<TopArtistsResponse>) -> Unit)

}