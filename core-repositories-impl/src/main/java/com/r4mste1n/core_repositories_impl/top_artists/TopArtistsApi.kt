package com.r4mste1n.core_repositories_impl.top_artists

import com.r4mste1n.core_common.models.responses.TopArtistsResponse
import retrofit2.http.GET
import retrofit2.http.Query

/**
 * Created by Alex Shtain on 28.06.2020.
 */
interface TopArtistsApi {

    @GET("?method=chart.gettopartists")
    suspend fun getTopArtists(
        @Query("limit") limit: Int
    ): TopArtistsResponse

}