package com.r4mste1n.core_repositories_api

import androidx.lifecycle.LiveData
import com.r4mste1n.core_common.Result
import com.r4mste1n.core_common.models.responses.TopTracksResponse

/**
 * Created by Alex Shtain on 22.07.2020.
 */
interface TopTracksRepoApi {

    val topTracks: LiveData<Result<TopTracksResponse>>

    suspend fun getTopTracks()

}