package com.r4mste1n.lastfmmusicmvvm.main.repositories.top_artists

import com.r4mste1n.lastfmmusicmvvm.main.top_artists.models.TopArtistsResponse
import com.r4mste1n.lastfmmusicmvvm.root.network.ErrorUtils
import com.r4mste1n.lastfmmusicmvvm.root.network.Result
import com.r4mste1n.lastfmmusicmvvm.root.network.api.LastFmApiHelper
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import retrofit2.HttpException

/**
 * Created by Alex Shtain on 03.05.2020.
 */

private const val TOP_ARTISTS_LIMIT = 100

class TopArtistsRepository : TopArtistsRepositoryContract {

    private var topArtists: TopArtistsResponse? = null

    override suspend fun loadTopArtists(listener: (Result<TopArtistsResponse>) -> Unit) {

        topArtists?.let {
            listener(Result.Success(it))
            return
        }

        listener(Result.IsLoading)

        var errorMessage: String? = null
        var response: TopArtistsResponse? = null

        CoroutineScope(Dispatchers.IO).launch {
            try {
                response = LastFmApiHelper.lastFmApi.getTopArtists(
                    limit = TOP_ARTISTS_LIMIT
                )
            } catch (exception: HttpException) {
                errorMessage = ErrorUtils.parseError(exception.response()?.errorBody())
            } catch (exception: Exception) {
                errorMessage = exception.message
            }
        }.join()

        when (response) {
            null -> listener(Result.Error(errorMessage))
            else -> {
                topArtists = response
                listener(Result.Success(response!!))
            }
        }
    }

}