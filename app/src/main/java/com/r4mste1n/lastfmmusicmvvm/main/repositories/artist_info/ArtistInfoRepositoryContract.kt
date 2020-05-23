package com.r4mste1n.lastfmmusicmvvm.main.repositories.artist_info

import androidx.lifecycle.LiveData
import com.r4mste1n.lastfmmusicmvvm.main.artist_info.models.ArtistInfoResponse
import com.r4mste1n.lastfmmusicmvvm.root.network.Result

/**
 * Created by Alex Shtain on 18.04.2020.
 */
interface ArtistInfoRepositoryContract {

    val artistInfo: LiveData<Result<ArtistInfoResponse>>

    suspend fun loadArtistInfo(artistName: String)

}