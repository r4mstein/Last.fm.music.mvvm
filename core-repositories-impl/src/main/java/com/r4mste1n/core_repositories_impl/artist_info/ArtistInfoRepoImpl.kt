package com.r4mste1n.core_repositories_impl.artist_info

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.r4mste1n.core_common.Result
import com.r4mste1n.core_common.models.responses.ArtistInfoResponse
import com.r4mste1n.core_network_api.ErrorUtilsApi
import com.r4mste1n.core_network_api.HttpClientApi
import com.r4mste1n.core_repositories_api.ArtistInfoRepoApi
import retrofit2.HttpException
import javax.inject.Inject

/**
 * Created by Alex Shtain on 18.04.2020.
 */

class ArtistInfoRepoImpl @Inject constructor(
    private val httpClient: HttpClientApi,
    private val errorUtils: ErrorUtilsApi
) : ArtistInfoRepoApi {

    private val _artistInfo = MutableLiveData<Result<ArtistInfoResponse>>()

    override val artistInfo: LiveData<Result<ArtistInfoResponse>>
        get() = _artistInfo

    override suspend fun loadArtistInfo(artistName: String) {

        _artistInfo.postValue(Result.IsLoading)

        var errorMessage: String? = null
        var response: ArtistInfoResponse? = null

        try {

            response = httpClient.getApi(ArtistInfoApi::class.java).getArtistInfo(
                artist = artistName
            )

        } catch (exception: HttpException) {

            errorMessage = errorUtils.parseError(exception.response()?.errorBody())

        } catch (exception: Exception) {

            errorMessage = exception.message

        }

        when (response) {
            null -> _artistInfo.postValue(Result.Error(errorMessage))
            else -> _artistInfo.postValue(Result.Success(response))
        }
    }

}