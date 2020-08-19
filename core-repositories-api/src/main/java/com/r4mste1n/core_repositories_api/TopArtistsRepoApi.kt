package com.r4mste1n.core_repositories_api

import androidx.lifecycle.LiveData
import com.r4mste1n.core_common.Result
import com.r4mste1n.core_common.models.data.TopArtistData

/**
 * Created by Alex Shtain on 03.05.2020.
 */
interface TopArtistsRepoApi {

    val topArtists: LiveData<Result<List<TopArtistData>>>

    suspend fun getTopArtists()

}