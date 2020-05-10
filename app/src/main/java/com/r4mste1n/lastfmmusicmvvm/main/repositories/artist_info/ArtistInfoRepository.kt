package com.r4mste1n.lastfmmusicmvvm.main.repositories.artist_info

import com.r4mste1n.lastfmmusicmvvm.main.artist_info.models.ArtistInfoResponse
import com.r4mste1n.lastfmmusicmvvm.root.network.ErrorUtils
import com.r4mste1n.lastfmmusicmvvm.root.network.Result
import com.r4mste1n.lastfmmusicmvvm.root.network.api.LastFmApiHelper
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import retrofit2.HttpException

/**
 * Created by Alex Shtain on 18.04.2020.
 */

class ArtistInfoRepository : ArtistInfoRepositoryContract {

    override suspend fun loadArtistInfo(
        artistName: String,
        listener: (Result<ArtistInfoResponse>) -> Unit
    ) {

        listener(Result.IsLoading)

        var errorMessage: String? = null
        var response: ArtistInfoResponse? = null

        CoroutineScope(Dispatchers.IO).launch() {
            try {
                response = LastFmApiHelper.lastFmApi.getArtistInfo(
                    artist = artistName
                )
            } catch (exception: HttpException) {
                errorMessage = ErrorUtils.parseError(exception.response()?.errorBody())
            } catch (exception: Exception) {
                errorMessage = exception.message
            }
        }.join()

        when (response) {
            null -> listener(Result.Error(errorMessage))
            else -> listener(Result.Success(response!!))
        }
    }

}