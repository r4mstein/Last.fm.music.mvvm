package com.r4mste1n.lastfmmusicmvvm.main.repositories.top_artists

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.r4mste1n.lastfmmusicmvvm.main.top_artists.models.TopArtistsResponse
import com.r4mste1n.lastfmmusicmvvm.root.network.ErrorUtils
import com.r4mste1n.lastfmmusicmvvm.root.network.Result
import com.r4mste1n.lastfmmusicmvvm.root.network.api.LastFmApiHelper
import retrofit2.HttpException

/**
 * Created by Alex Shtain on 03.05.2020.
 */

class TopArtistsRepository : TopArtistsRepositoryContract {

    private val _topArtists = MutableLiveData<Result<TopArtistsResponse>>()

    override val topArtists: LiveData<Result<TopArtistsResponse>>
        get() = _topArtists

    override suspend fun getTopArtists() {

        if (_topArtists.value is Result.Success) {
            return
        }

        _topArtists.postValue(Result.IsLoading)

        var errorMessage: String? = null
        var response: TopArtistsResponse? = null

        try {
            response = LastFmApiHelper.lastFmApi.getTopArtists(
                limit = TOP_ARTISTS_LIMIT
            )
        } catch (exception: HttpException) {
            errorMessage = ErrorUtils.parseError(exception.response()?.errorBody())
        } catch (exception: Exception) {
            errorMessage = exception.message
        }

        when (response) {
            null -> {
                _topArtists.postValue(Result.Error(errorMessage))
            }
            else -> {
                _topArtists.postValue(Result.Success(response))
            }
        }

    }

    companion object {

        private const val TOP_ARTISTS_LIMIT = 100

    }

}