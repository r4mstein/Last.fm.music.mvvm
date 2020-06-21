package com.r4mste1n.lastfmmusicmvvm.main.repositories.artist_info

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.r4mste1n.lastfmmusicmvvm.main.artist_info.models.ArtistInfoResponse
import com.r4mste1n.lastfmmusicmvvm.root.network.ErrorUtils
import com.r4mste1n.lastfmmusicmvvm.root.network.Result
import com.r4mste1n.lastfmmusicmvvm.root.network.api.LastFmApi
import retrofit2.HttpException
import javax.inject.Inject

/**
 * Created by Alex Shtain on 18.04.2020.
 */

class ArtistInfoRepository @Inject constructor(
    private val lastFmApi: LastFmApi
) : ArtistInfoRepositoryContract {

    private val _artistInfo = MutableLiveData<Result<ArtistInfoResponse>>()

    override val artistInfo: LiveData<Result<ArtistInfoResponse>>
        get() = _artistInfo

    override suspend fun loadArtistInfo(artistName: String) {

        _artistInfo.postValue(Result.IsLoading)

        var errorMessage: String? = null
        var response: ArtistInfoResponse? = null

        try {
            response = lastFmApi.getArtistInfo(
                artist = artistName
            )
        } catch (exception: HttpException) {
            errorMessage = ErrorUtils.parseError(exception.response()?.errorBody())
        } catch (exception: Exception) {
            errorMessage = exception.message
        }

        when (response) {
            null -> _artistInfo.postValue(Result.Error(errorMessage))
            else -> _artistInfo.postValue(Result.Success(response))
        }
    }

}