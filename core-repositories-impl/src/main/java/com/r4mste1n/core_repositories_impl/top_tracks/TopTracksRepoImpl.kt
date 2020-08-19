package com.r4mste1n.core_repositories_impl.top_tracks

import androidx.lifecycle.LiveData
import androidx.lifecycle.MediatorLiveData
import com.r4mste1n.core_common.Result
import com.r4mste1n.core_common.models.data.TopTrackData
import com.r4mste1n.core_common.models.responses.TopTracksResponse
import com.r4mste1n.core_db_api.DBManagerApi
import com.r4mste1n.core_network_api.ErrorUtilsApi
import com.r4mste1n.core_network_api.HttpClientApi
import com.r4mste1n.core_repositories_api.TopTracksRepoApi
import com.r4mste1n.core_repositories_impl.repo_mapper.RepoMapperApi
import retrofit2.HttpException
import javax.inject.Inject

/**
 * Created by Alex Shtain on 22.07.2020.
 */
class TopTracksRepoImpl @Inject constructor(
    private val httpClient: HttpClientApi,
    private val errorUtils: ErrorUtilsApi,
    private val dbManager: DBManagerApi,
    private val repoMapper: RepoMapperApi
) : TopTracksRepoApi {

    private val _topTracks by lazy {
        MediatorLiveData<Result<List<TopTrackData>>>()
    }

    override val topTracks: LiveData<Result<List<TopTrackData>>>
        get() = _topTracks

    override suspend fun getTopTracks() {

        if (_topTracks.value is Result.Success) {
            return
        }

        _topTracks.postValue(Result.IsLoading)
        getTracksFromDB()

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
                dbManager.apply {
                    clearAllTopTracks()

                    insertTopTracks(response)
                }

                _topTracks.postValue(Result.Success(repoMapper.convertTopTracks(response)))
            }
        }
    }

    private suspend fun getTracksFromDB() {
        val dbSource = dbManager.getTopTracks()

        _topTracks.addSource(dbSource) { artists ->
            if (!artists.isNullOrEmpty()) {
                _topTracks.removeSource(dbSource)

                _topTracks.postValue(Result.Success(artists))
            }
        }
    }

    companion object {

        private const val TOP_TRACKS_LIMIT = 100

    }

}