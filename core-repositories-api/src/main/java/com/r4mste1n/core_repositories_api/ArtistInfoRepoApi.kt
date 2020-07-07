package com.r4mste1n.core_repositories_api

import androidx.lifecycle.LiveData
import com.r4mste1n.core_common.Result
import com.r4mste1n.core_common.models.responses.ArtistInfoResponse

/**
 * Created by Alex Shtain on 18.04.2020.
 */
interface ArtistInfoRepoApi {

    val artistInfo: LiveData<Result<ArtistInfoResponse>>

    suspend fun loadArtistInfo(artistName: String)

}