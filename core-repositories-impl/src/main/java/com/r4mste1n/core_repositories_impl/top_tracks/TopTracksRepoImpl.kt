package com.r4mste1n.core_repositories_impl.top_tracks

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.r4mste1n.core_common.Result
import com.r4mste1n.core_common.models.responses.TopTracksResponse
import com.r4mste1n.core_network_api.ErrorUtilsApi
import com.r4mste1n.core_network_api.HttpClientApi
import com.r4mste1n.core_repositories_api.TopTracksRepoApi
import retrofit2.HttpException
import javax.inject.Inject

/**
 * Created by Alex Shtain on 22.07.2020.
 */
class TopTracksRepoImpl @Inject constructor(
    private val httpClient: HttpClientApi,
    private val errorUtils: ErrorUtilsApi
) : TopTracksRepoApi {

    private val _topTracks = MutableLiveData<Result<TopTracksResponse>>()

    override val topTracks: LiveData<Result<TopTracksResponse>>
        get() = _topTracks

    override suspend fun getTopTracks() {

        if (_topTracks.value is Result.Success) {
            return
        }

        _topTracks.postValue(Result.IsLoading)

        var errorMessage: String? = null
        var response: TopTracksResponse? = null

        try {

            response = httpClient.getApi(TopTracksApi::class.java).getTopTracks(
                limit = TOP_TRACKS_LIMIT
            )

        } catch (exception: HttpException) {

            errorMessage = errorUtils.parseError(exception.response()?.errorBody())

        } catch (exception: Exception) {

            errorMessage = exception.message

        }

        when (response) {
            null -> {
                _topTracks.postValue(Result.Error(errorMessage))
            }
            else -> {
                _topTracks.postValue(Result.Success(response))
            }
        }
    }

    companion object {

        private const val TOP_TRACKS_LIMIT = 100

    }

}