package com.r4mste1n.core_repositories_impl.artist_info

import com.r4mste1n.core_common.models.responses.ArtistInfoResponse
import retrofit2.http.GET
import retrofit2.http.Query

/**
 * Created by Alex Shtain on 29.06.2020.
 */
interface ArtistInfoApi {

    @GET("?method=artist.getinfo")
    suspend fun getArtistInfo(
        @Query("artist") artist: String
    ): ArtistInfoResponse

}