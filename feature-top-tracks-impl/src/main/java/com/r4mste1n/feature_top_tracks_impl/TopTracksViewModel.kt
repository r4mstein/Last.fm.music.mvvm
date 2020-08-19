package com.r4mste1n.feature_top_tracks_impl

import androidx.lifecycle.*
import com.r4mste1n.core_common.Result
import com.r4mste1n.core_common.models.data.TopTrackData
import com.r4mste1n.core_repositories_api.TopTracksRepoApi
import com.r4mste1n.feature_top_tracks_impl.adapter.AdapterData
import kotlinx.coroutines.launch
import javax.inject.Inject

/**
 * Created by Alex Shtain on 22.07.2020.
 */
class TopTracksViewModel @Inject constructor(
    private val repository: TopTracksRepoApi
) : ViewModel(), Contract.ViewModel {

    private val _topArtists = MutableLiveData<Result<ArrayList<AdapterData>>>()

    override val topTracks: LiveData<Result<ArrayList<AdapterData>>>
        get() = Transformations.switchMap(repository.topTracks) {

            _topArtists.postValue(getUiResult(it))

            return@switchMap _topArtists
        }

    private fun getUiResult(result: Result<List<TopTrackData>>): Result<ArrayList<AdapterData>> {
        return when (result) {
            is Result.IsLoading -> {
                Result.IsLoading
            }
            is Result.Error -> {
                Result.Error(result.message)
            }
            is Result.Success -> {
                Result.Success(convertLoadedDataToUiData(result.data))
            }
        }
    }

    override fun getTracks() {
        viewModelScope.launch {
            repository.getTopTracks()
        }
    }

    private fun convertLoadedDataToUiData(tracks: List<TopTrackData>) =
        ArrayList<AdapterData>().apply {
            tracks.forEach { track ->
                add(
                    AdapterData(
                        name = track.name,
                        singer = track.singer,
                        photoUrl = track.photoUrl
                    )
                )
            }
        }

}