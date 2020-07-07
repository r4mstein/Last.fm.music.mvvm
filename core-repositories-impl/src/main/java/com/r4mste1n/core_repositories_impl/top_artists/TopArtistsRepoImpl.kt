package com.r4mste1n.core_repositories_impl.top_artists

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.r4mste1n.core_common.Result
import com.r4mste1n.core_common.models.responses.TopArtistsResponse
import com.r4mste1n.core_network_api.ErrorUtilsApi
import com.r4mste1n.core_network_api.HttpClientApi
import com.r4mste1n.core_repositories_api.TopArtistsRepoApi
import retrofit2.HttpException
import javax.inject.Inject

/**
 * Created by Alex Shtain on 03.05.2020.
 */

class TopArtistsRepoImpl @Inject constructor(
    private val httpClient: HttpClientApi,
    private val errorUtils: ErrorUtilsApi
) : TopArtistsRepoApi {

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

            response = httpClient.getApi(TopArtistsApi::class.java).getTopArtists(
                limit = TOP_ARTISTS_LIMIT
            )

        } catch (exception: HttpException) {

            errorMessage = errorUtils.parseError(exception.response()?.errorBody())

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