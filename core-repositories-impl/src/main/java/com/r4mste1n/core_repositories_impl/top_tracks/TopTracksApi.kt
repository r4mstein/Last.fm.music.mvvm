package com.r4mste1n.core_repositories_impl.top_tracks

import com.r4mste1n.core_common.models.responses.TopTracksResponse
import retrofit2.http.GET
import retrofit2.http.Query

/**
 * Created by Alex Shtain on 22.07.2020.
 */
interface TopTracksApi {

    @GET("?method=chart.gettoptracks")
    suspend fun getTopTracks(
        @Query("limit") limit: Int
    ): TopTracksResponse

}